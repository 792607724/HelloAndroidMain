package com.example.helloandroid;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity14 extends AppCompatActivity {
    private Button get, post, json, imageRequest;
    private ImageView iv_volley;
    private NetworkImageView iv_network;
    private TextView tvVolleyResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();
        initListener();
    }

    private void initListener() {
        // get button
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建一个请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity14.this);
                // 创建一个请求
                String url = "https://baike.baidu.com/item/%E6%83%85%E4%BA%BA%E8%8A%82/30001?fr=aladdin";
                StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                    // 正确接收数据后的回调
                    @Override
                    public void onResponse(String response) {
                        tvVolleyResult.setText(response);
                    }
                },
                        //发生异常之后的监听回调
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                tvVolleyResult.setText("加载错误：" + error);
                            }
                        });
                // 将创建的请求添加到请求队列中
                requestQueue.add(stringRequest);
            }
        });
        // post button
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建一个请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity14.this);
                String url = "https://baike.baidu.com/item/%E6%83%85%E4%BA%BA%E8%8A%82/30001?fr=aladdin";
                // 创建一个请求
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    // 正确接收数据后的回调
                    @Override
                    public void onResponse(String response) {
                        tvVolleyResult.setText(response);
                    }
                }, //发生异常后的监听回调
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                tvVolleyResult.setText("加载错误：" + error);
                            }
                        }) {
                    // 设置需要Post的参数
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
//                        map.put("value1", "param1");
                        return map;
                    }
                };
                // 将创建的请求添加到请求队列中
                requestQueue.add(stringRequest);
            }
        });


        // json button
        json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1、创建一个请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity14.this);
                String url = "https://baike.baidu.com/item/%E6%83%85%E4%BA%BA%E8%8A%82/30001?fr=aladdin";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        tvVolleyResult.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvVolleyResult.setText("加载错误：" + error);
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });
        // image request button
        imageRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建一个请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity14.this);
                String url = "http://health.people.com.cn/NMediaFile/2016/1129/MAIN201611291650433728690070831.jpg";
                // 创建一个图片请求
                ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                    // 正确接收到图片
                    @Override
                    public void onResponse(Bitmap response) {
                        iv_volley.setVisibility(View.VISIBLE);
                        iv_volley.setImageBitmap(response);
                    }
                },
                        // 前面两个0，表示加载图片的最大宽高，后面的表示图片质量，5+6+5=16位，位数越高图片质量越好
                        0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity14.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
                        iv_volley.setVisibility(View.VISIBLE);
                        iv_volley.setImageResource(R.drawable.mine_page);
                    }
                });
                requestQueue.add(imageRequest);
            }
        });

    }

    private void initView() {
        get = findViewById(R.id.get);
        post = findViewById(R.id.post);
        json = findViewById(R.id.json);
        imageRequest = findViewById(R.id.imageRequest);
        iv_volley = findViewById(R.id.iv_volley);
        iv_network = findViewById(R.id.iv_network);
        tvVolleyResult = findViewById(R.id.tvVolleyResult);
    }


}