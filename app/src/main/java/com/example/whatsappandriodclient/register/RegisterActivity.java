//package com.example.whatsappandriodclient.register;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProvider;
//
//import com.example.whatsappandriodclient.R;
////import com.example.whatsappandriodclient.databinding.ActivityRegisterBinding;
////import com.example.whatsappandriodclient.ui.login.LoginFormState;
//import com.example.whatsappandriodclient.ui.login.LoginViewModel;
//import com.example.whatsappandriodclient.ui.login.LoginViewModelFactory;
//
//public class RegisterActivity extends AppCompatActivity {
//
////    private LoginViewModel loginViewModel;
//    private ActivityRegisterBinding binding;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
////        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
////        setContentView(binding.getRoot());
//
////        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
////                .get(LoginViewModel.class);
//
////        final EditText usernameEditText = binding.username;
////        final EditText passwordEditText = binding.password;
////        final EditText nicknameEditText = binding.nickname;
////        final EditText confirmPasswordEditText = binding.confirm;
////        final Button loginButton = binding.login;
////        final ProgressBar loadingProgressBar = binding.loading;
//
////        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
////            @Override
////            public void onChanged(@Nullable LoginFormState loginFormState) {
////                if (loginFormState == null) {
////                    return;
////                }
////                loginButton.setEnabled(loginFormState.isDataValid());
////                if (loginFormState.getUsernameError() != null) {
////                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
////                }
////                if (loginFormState.getPasswordError() != null) {
////                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
////                }
////            }
////        });
//    }
//}
