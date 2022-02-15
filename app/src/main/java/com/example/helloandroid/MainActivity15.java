package com.example.helloandroid;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity15 extends AppCompatActivity {

    // authorities下的${applicationId}在gradle编译时会自动替换当前app项目的包名
    /**
     * Android7.0 （N） 开始，将严格执行 StrictMode 模式，也就是说，将对安全做更严格的校验。而从 Android N 开始，将不允许在 App 间，
     * 使用 file:// 的方式，传递一个 File ，否者会抛出 FileUriExposedException的错误，会直接引发 Crash。
     * 但是，既然官方对文件的分享做了一个这么强硬的修改（直接抛出异常），实际上也提供了解决方案，那就是 FileProvider，
     * 通过 content://的模式替换掉 file://，同时，需要开发者主动升级 targetSdkVersion 到 24 才会执行此策略。
     * FileProvider是android support v4包提供的，是ContentProvider的子类，便于将自己app的数据提供给其他app访问。
     * 在app开发过程中需要用到FileProvider的主要有
     * <p>
     * 相机拍照以及图片裁剪
     * 调用系统应用安装器安装apk（应用升级）
     */

    private ImageView photoViewer, cameraBtn;
    private TextView tv_tip_text;
    private static final int CAMERA_REQUEST_CODE = 0x00000010;
    private static final int PERMISSION_CAMERA_REQUEST_CODE = 0x00000012;
    private Uri mCameraUri;
    private String mCameraImagePath;
    private boolean isAndroidQ = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main15);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissionAndCamera();
            }
        });


    }

    private void checkPermissionAndCamera() {
        // 动态检查相机权限
        int hasCameraPermission = ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.CAMERA);
        // 检查结果
        if (hasCameraPermission == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            // 请求权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA_REQUEST_CODE);
        }
    }

    // 请求权限的回调函数，请求权限完成后触发
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CAMERA_REQUEST_CODE) {
            // 授权成功后
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                // 未授权后
                Toast.makeText(this, "请先给机器授予拍照权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // onActivityResult()新的Activity关闭后要向前面的Activity传回数据
    // startActivityForResult() 与上面对应，新的Activity需要传回前面的Activity的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 判断是否与startActivityForResult()的requestCode对应来获取数据
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (isAndroidQ) {
                    // 展示图片 通过图片Uri加载图片
                    photoViewer.setImageURI(mCameraUri);
                } else {
                    // 展示图片 通过位图Bitmap加载图片
                    photoViewer.setImageBitmap(BitmapFactory.decodeFile(mCameraImagePath));
                }
            } else {
                Toast.makeText(this, "取消操作", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void openCamera() {
        // 跳转至Camera的Action
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // resolveActivity判断能否找到Camera Activity，找不到返回null
        if (captureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            Uri photoUri = null;
            /**
             Android Q文件存储机制修改成了沙盒模式，类似于iOS
             应用只能访问自己沙盒下的文件和公共媒体文件
             **/
            if (isAndroidQ) {
                // 获得返回的照片Uri
                photoUri = createImageUri();
            } else {
                try {
                    // 获得返回照片的文件
                    photoFile = createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (photoFile != null) {
                    // 获取照片的绝对路径
                    mCameraImagePath = photoFile.getAbsolutePath();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        // "${applicationId}.fileprovider"，Android N以后都不通过FileProvider获取Uri，需要通过指定file path从整个手机内存空间内进行共享获取该照片的Uri
                        // FileProvider 是一个特殊的 ContentProvider 的子类，它通过使用 content:// Uri 代替了 file:/// Uri. ，来为另一个app分享文件
                        photoUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", photoFile);
                    } else {
                        // 从文件创建Uri属于：file:/// Uri.
                        photoUri = Uri.fromFile(photoFile);
                    }
                }
            }
            mCameraUri = photoUri;
            if (photoUri != null) {
                // 多媒体文件照片、视频的特定ContentResolver存储格式MediaStore.EXTRA_OUTPUT
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                // 给Intent授权：对URI的写入权限
                captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                // Camera Activity关闭时回传给前Activity的数据
                startActivityForResult(captureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

    private Uri createImageUri() {
        // 返回主要共享、外部存储媒体的当前状态
        String status = Environment.getExternalStorageState();
        // 存储状态，如果媒体存在并安装在其安装点并具有读写权限，SD卡
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            // 通过实例化ContentResolver对provider的访问，如果有对装载点的读写权限，则将照片写入sdcard下的多媒体文件中
            // ContentValues():存储ContentResolver可以存储的一组值
            return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        } else {
            // 如果对装载点没有读写权限，将照片写入system的多媒体文件
            return getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, new ContentValues());
        }
    }

    private File createImageFile() throws IOException {
        String imageName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        // 获取公共图片的标准目录存储文件夹
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (!storageDir.exists()) {
            // 文件夹不存在就直接创建
            storageDir.mkdir();
        }
        // 创建图片文件，传入文件夹和图片的名称
        File tempFile = new File(storageDir, imageName);
        // 获取给定设备的路径的存储状态
        if (!Environment.MEDIA_MOUNTED.equals(EnvironmentCompat.getStorageState(tempFile))) {
            return null;
        }
        return tempFile;
    }


    private void initView() {
        photoViewer = findViewById(R.id.photoViewer);
        cameraBtn = findViewById(R.id.cameraBtn);
        tv_tip_text = findViewById(R.id.tv_tip_text);

    }
}