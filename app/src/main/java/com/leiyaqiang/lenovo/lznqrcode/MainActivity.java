package com.leiyaqiang.lenovo.lznqrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

/**
 *  Created by Lzn__leiyaqiang on 2017/5/3
 */


public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private EditText mEditText;
    private ImageView mImageView;
//    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mTextView= (TextView) this.findViewById(R.id.tv_showResult);
        mEditText= (EditText) this.findViewById(R.id.et_text);
        mImageView= (ImageView) this.findViewById(R.id.img_shouw);
//        mCheckBox= (CheckBox) this.findViewById(R.id.cb_logo);
    }

    //扫描二维码
    //https://cli.im/text?2dd0d2b267ea882d797f03abf5b97d88二维码生成网站
    public void scan(View view) {
        startActivityForResult(new Intent(this, CaptureActivity.class),0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String result=bundle.getString("result");
                mTextView.setText(result);
            }
        }

    }

    //生成二维码 可以设置Logo
    public void make(View view) {

        String input = mEditText.getText().toString();
        if (input.equals("")){
            Toast.makeText(this,"输入不能为空",Toast.LENGTH_SHORT).show();
        }else{
            //设置扫描的结果和logo
            Bitmap qrCode = EncodingUtils.createQRCode(input, 500, 500,
                    true? BitmapFactory.decodeResource(getResources(),R.drawable.p14):null);
//                    mCheckBox.isChecked()? BitmapFactory.decodeResource(getResources(),R.drawable.p14):null);
            //显示二维码
            mImageView.setImageBitmap(qrCode);
        }
    }
}
