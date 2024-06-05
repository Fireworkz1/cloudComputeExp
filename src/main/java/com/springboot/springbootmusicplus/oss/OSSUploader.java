package com.springboot.springbootmusicplus.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class OSSUploader {

    public String uploadFile(MultipartFile multipartFile, String filename,String type) throws com.aliyuncs.exceptions.ClientException {

        String endpoint = "oss-cn-wulanchabu.aliyuncs.com";
        String fileEndpoint = "https://fireworkz.oss-cn-wulanchabu.aliyuncs.com";
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "fireworkz";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName;
        if(type.equals("music")){
            objectName = "music/" + filename;
        } else if (type.equals("pic")) {
            objectName = "pic/" + filename;
        }else {
            throw new RuntimeException("fake type");
        }

        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。

        String tempFilePath = "/temp/" + filename;
        // 创建一个临时文件
        File file = new File(tempFilePath);
        try {
            // 将 MultipartFile 转换为 File
            multipartFile.transferTo(file);

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

            try {
                // 创建PutObjectRequest对象。
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, file);
                // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
                 ObjectMetadata metadata = new ObjectMetadata();
                 metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
                 metadata.setObjectAcl(CannedAccessControlList.PublicReadWrite);
                 putObjectRequest.setMetadata(metadata);
                // 上传文件。
                PutObjectResult result = ossClient.putObject(putObjectRequest);
            } catch (OSSException oe) {
                System.out.println("Caught an OSSException, which means your request made it to OSS, "
                        + "but was rejected with an error response for some reason.");
                System.out.println("Error Message:" + oe.getErrorMessage());
                System.out.println("Error Code:" + oe.getErrorCode());
                System.out.println("Request ID:" + oe.getRequestId());
                System.out.println("Host ID:" + oe.getHostId());
            } catch (ClientException ce) {
                System.out.println("Caught an ClientException, which means the client encountered "
                        + "a serious internal problem while trying to communicate with OSS, "
                        + "such as not being able to access the network.");
                System.out.println("Error Message:" + ce.getMessage());
            } finally {
                if (ossClient != null) {
                    ossClient.shutdown();
                }
            }
            // 上传成功后，删除临时文件
            if (file.exists()) {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClientException("File conversion failed", e);
        }
        return fileEndpoint + '/' + objectName;
    }
}
