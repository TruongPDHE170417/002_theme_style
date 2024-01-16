package com.truongpd11.a002_theme_style;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // khi được tạo ra: một trong những bước của lifecycle call back
    // định nghĩa trước khi view render ra thì thực hiện câu lệnh nào
    /* document.onReady: trước khi hiện ra web browser thì sẽ load hết element rồi chạy call back
     *function -> chạy source
     * Tương tự ở đây: khi chạy qua setContentView thì các element trong xml mới được load
     *   => lúc đấy mới viết code
     * onCreate là call back function nơi chuẩn bị giao diện để hiện ra bên ngoài
     * */
    Button btnShowData;
    TextView tvShowData;
    EditText edtShowData;

    private void bindingView(){
        btnShowData = findViewById(R.id.btnShowData);
        tvShowData = findViewById(R.id.tvShowData);
        edtShowData = findViewById(R.id.edtShowData);
    }
    private void bindingAction(){
        //gõ setListenner thì IDE sẽ gợi ý
        Context that = this; // var that = this để chuyền context bên ngoài vào function
        btnShowData.setOnClickListener(this::onBtnShowData);
        btnShowData.setOnLongClickListener(this::onBtnLongShowData);
        edtShowData.setOnFocusChangeListener(this::onEdtDataFocusChange);
        tvShowData.setOnClickListener(this::setData);
    }

    private void onEdtDataFocusChange(View view, boolean b) {
        if(b){
            edtShowData.setBackgroundResource(R.color.cyan);
        }else{
            edtShowData.setBackgroundResource(R.color.white);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //code style here ...


        //Biding view
        //Công việc đánh số cho id thì build tool tự làm (int: resource id)
        //Id unique trên toàn bộ project
        //phải có binding view trước mới có thể binding action
        // có cách nào để đảm bảo được không? ==> lỗi nhảm shit :D
        // ==> dùng template sau bindingView và binding action
        bindingView();
        bindingAction();

        //gắn dữ kiện theo method reference
//        btnShowData.setOnClickListener(this::onBtnShowData);
//        btnShowData.setOnLongClickListener(this::onBtnLongShowData);
        //holy short cut: alt+enter
        // bất cứ chỗ nào đỏ thì alt enter tới chết :D


        //Actions handling
//        btnShowData.setOnClickListener((event)->{
//            //call-back function
//            //đây không phải là cú pháp chính thống của java
//            // đúng là new interface rồi sau đó với implemment
//        });
        //theo IDE, chỗ nào xám thì có 2 lý do không dùng | có cách nhanh hơn
//        btnShowData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String data = edtShowData.getText().toString();
//                tvShowData.setText(data);
//            }
//        });
        //==>
//        btnShowData.setOnClickListener(v -> {
//            String data = edtShowData.getText().toString();
//            tvShowData.setText(data);
//        });

        //khi focus vào editText
//        edtShowData.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//              nếu this ở đây thì this sẽ là context của OnFocusChangeListener
//            }
//        });

        //JS cho viết hàm trong hàm :D
        //! Chúng ta không được phép truyền UI giữa các hàm ( chỉ làm trên trang xử lý UI thôi) không được phép chuyển trong hàm và class khác
        // Solution: khai báo trước rồi khởi tạo trong oncreate()
//        tvShowData.setOnClickListener(v -> setData(v));
        // rút gọn hơn bằng việc dùng method reference
//        tvShowData.setOnClickListener(this::setData);

    }

    private boolean onBtnLongShowData(View view) {
        String data = edtShowData.getText().toString();
        //this tượng trưng cho main activity
        Toast.makeText(this,"Long Click on",Toast.LENGTH_LONG);
        return true;
    }

    private void onBtnShowData(View view) {
        String data = edtShowData.getText().toString();
        tvShowData.setText(data);
    }

    //View ở đây giống với object event
    void setData(View v) {
        String data = edtShowData.getText().toString();
        tvShowData.setText(data);
    }


    public void onBtnClick(View view) {
        onBtnShowData(view);
    }
    

}