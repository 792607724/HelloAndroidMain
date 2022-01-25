package com.example.helloandroid;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity6 extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout chat, addressbook, explorer, mine;
    private ChatFragment chatFragment;
    private AddressBookFragment addressBookFragment;
    private ExplorerFragment explorerFragment;
    private MineFragment mineFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        this.transparentStatusBar();
        this.initControls();

    }

    private void transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void initControls() {
        chat = findViewById(R.id.chat);
        addressbook = findViewById(R.id.addressbook);
        explorer = findViewById(R.id.explorer);
        mine = findViewById(R.id.mine);

        chat.setOnClickListener(this);
        addressbook.setOnClickListener(this);
        explorer.setOnClickListener(this);
        mine.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        chat.performClick();

    }

    @Override
    public void onClick(View view) {
        // 只能是局部变量，不能是全局变量，否则不能commit
        // fragmentTransaction只能使用一次
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        this.hideAllFragment(fragmentTransaction);
        switch (view.getId()) {
            case R.id.chat:
                this.setAllSelectedFalse();
                chat.setSelected(true);
                // 保证只实例化一次
                if (chatFragment == null) {
                    chatFragment = new ChatFragment("Chat");
                    fragmentTransaction.add(R.id.tab_display, chatFragment);
                } else {
                    fragmentTransaction.show(chatFragment);
                }
                break;
            case R.id.addressbook:
                this.setAllSelectedFalse();
                addressbook.setSelected(true);
                if (addressBookFragment == null) {
                    addressBookFragment = new AddressBookFragment("AddressBook");
                    fragmentTransaction.add(R.id.tab_display, addressBookFragment);
                } else {
                    fragmentTransaction.show(addressBookFragment);
                }
                break;
            case R.id.explorer:
                this.setAllSelectedFalse();
                explorer.setSelected(true);
                if (explorerFragment == null) {
                    explorerFragment = new ExplorerFragment("Explorer");
                    fragmentTransaction.add(R.id.tab_display, explorerFragment);
                } else {
                    fragmentTransaction.show(explorerFragment);
                }
                break;
            case R.id.mine:
                this.setAllSelectedFalse();
                mine.setSelected(true);
                if (mineFragment == null) {
                    mineFragment = new MineFragment("Mine");
                    fragmentTransaction.add(R.id.tab_display, mineFragment);
                } else {
                    fragmentTransaction.show(mineFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (chatFragment != null) {
            fragmentTransaction.hide(chatFragment);
        }
        if (addressBookFragment != null) {
            fragmentTransaction.hide(addressBookFragment);
        }
        if (explorerFragment != null) {
            fragmentTransaction.hide(explorerFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
    }

    private void setAllSelectedFalse() {
        chat.setSelected(false);
        addressbook.setSelected(false);
        explorer.setSelected(false);
        mine.setSelected(false);
    }
}