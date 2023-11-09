package lewis.com.sign.ui.act;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import butterknife.OnClick;
import lewis.com.sign.R;
import lewis.com.sign.base.BaseActivity;
import lewis.com.sign.bean.ComResult;
import lewis.com.sign.bean.FaceResult;
import lewis.com.sign.bean.KechengList;
import lewis.com.sign.camera.CameraPreview;
import lewis.com.sign.camera.OverCameraView;
import lewis.com.sign.utils.BitmapUtils;
import lewis.com.sign.utils.Contance;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SignAct extends BaseActivity {
    @BindView(R.id.camera)
    Button camera;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.camera_preview_layout)
    FrameLayout mPreviewLayout;

    /**
     * 是否正在聚焦
     */
    private boolean isFoucing;
    /**
     * 相机类
     */
    private Camera mCamera;
    /**
     * 聚焦视图
     */
    private OverCameraView mOverCameraView;

    /**
     * 拍照标记
     */
    private boolean isTakePhoto;
    private Runnable mRunnable;
    private Handler mHandler = new Handler() {

    };
    private KechengList.DataBean bean;

    @Override
    public int intiLayout() {
        return R.layout.act_sign;
    }

    @Override
    public void initView() {
        tvTitle.setText("人脸签到");
        ivBack.setVisibility(View.VISIBLE);
        Bundle extras = getIntent().getExtras();
        bean = (KechengList.DataBean) extras.getSerializable("bean");
        initCamera();
    }

    @Override
    public void initData() {

    }

    private void initCamera() {
        mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
//        mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        CameraPreview preview = new CameraPreview(this, mCamera);
        mOverCameraView = new OverCameraView(this);
        mPreviewLayout.addView(preview);
        mPreviewLayout.addView(mOverCameraView);
    }
    public void takePhoto() {
//        mIvTakePhoto = (ImageView) view;
//        mIvTakePhoto.setEnabled(false);
//        isTakePhoto = true;
        camera.setText("正在比对");
        camera.setEnabled(false);
        //调用相机拍照
        mCamera.takePicture(null, null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, null).copy(Bitmap.Config.ARGB_8888, true);



                //停止预览
                mCamera.stopPreview();
//                mCamera.stopFaceDetection();
                Paint paint = new Paint();
                paint.setColor(Color.WHITE);// 白色画笔
                paint.setTextSize(80.0f);// 设置字体大小
                Bitmap bitmap1 = BitmapUtils.createBitmap(bitmap.getWidth(), bitmap.getWidth());

                Canvas canvas = new Canvas(bitmap1);
                Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getWidth());
                Rect dst = new Rect(0, 0, bitmap.getWidth(), bitmap.getWidth());

                canvas.drawBitmap(bitmap, src, dst, paint);
                canvas.save();
                canvas.restore();
                bitmap1 = BitmapUtils.compressScaleBitmap(bitmap1, 400, 400);
                faceCheck(BitmapUtils.bitmapToBase64(bitmap1));
            }
        });
    }
    //百度智能云SDK人脸检测
    private void faceCheck(String img) {
        OkHttpUtils
                .post()
                .url(Contance.faceCheckBase64)
                .addParams("img", img)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("sss", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(Contance.addKecheng,response);
                        FaceResult login = new Gson().fromJson(response, FaceResult.class);
                        if (login != null &&login.data!=null&&login.data.result!=null&&login.data.result.user_list!=null&&login.data.result.user_list.size()>0) {
                            final FaceResult.DataBean.ResultBean.UserListBean listBean = login.data.result.user_list.get(0);
                            Log.d("TAG", "listBean.user_id = " + listBean.user_id);
                            Log.d("TAG", "listBean.user_info = " + listBean.user_info);
                            Log.d("TAG", "listBean.score = " + listBean.score);
                            if (listBean.getScore()>70){
                                toast("签到人：" + listBean.user_info);
                                tvTitle.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        addRecord(listBean.user_id);
                                    }
                                },1000);
                            }else {
                                toast("人脸不匹配请重新拍照");
                                Bundle bundle=new Bundle();
                                bundle.putSerializable("bean",bean);
                                jumpAct(SignAct.class,bundle);
                                finish();
                            }


                        }else {
                            toast("对不起，未能成功识别");
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("bean",bean);
                            jumpAct(SignAct.class,bundle);
                            finish();
                        }
                    }
                });

    }
    private void addRecord(String sid) {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        OkHttpUtils
                .post()
                .url(Contance.addRecord)
                .addParams("time",format)
                .addParams("kid",bean.id+"")
                .addParams("sid", sid)

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("sss", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ComResult login = new Gson().fromJson(response, ComResult.class);
                        if (login != null ) {

                            toast("签到成功");

                            Bundle bundle=new Bundle();
                            bundle.putSerializable("bean",bean);
                            jumpAct(SignAct.class,bundle);
                            finish();
                        }
                    }
                });

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!isFoucing) {
                float x = event.getX();
                float y = event.getY();
                isFoucing = true;
                if (mCamera != null && !isTakePhoto) {
                    mOverCameraView.setTouchFoucusRect(mCamera, autoFocusCallback, x, y);
                }
                mRunnable=new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SignAct.this, "自动聚焦超时,请调整合适的位置拍摄！", Toast.LENGTH_SHORT);
                        isFoucing = false;
                        mOverCameraView.setFoucuing(false);
                        mOverCameraView.disDrawTouchFocusRect();
                    }
                };
                //设置聚焦超时
                mHandler.postDelayed(mRunnable, 3000);
            }
        }
        return super.onTouchEvent(event);
    }


    private Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            isFoucing = false;
            mOverCameraView.setFoucuing(false);
            mOverCameraView.disDrawTouchFocusRect();
            //停止聚焦超时回调
//            mHandler.removeCallbacks(mRunnable);
        }
    };
    @OnClick({R.id.iv_back, R.id.camera})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.camera:
                takePhoto();
                break;
        }
    }
}
