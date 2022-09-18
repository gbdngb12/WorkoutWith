package com.bubu.workoutwithclient.userinterface.login

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bubu.workoutwithclient.databinding.LoginFragmentBinding
import com.bubu.workoutwithclient.retrofitinterface.*
import com.bubu.workoutwithclient.userinterface.MainScreenActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.EOFException
import java.lang.Exception
import java.net.SocketTimeoutException

suspend fun login(data: UserLoginData): Any? {
    val loginObject = UserLoginModule(data)
    val result = loginObject.getApiData()
    if (result is UserLoginResponseData) {
        return result
    } else if (result is UserError) {
        result.message.forEach {
            Log.d("UserError", it)
        }
        return result
    } else if (result is SocketTimeoutException) {
        return result
    } else if (result is EOFException) {
        return result
    } else if (result is Exception) {
        return result
    } else {
        return result
    }

}

suspend fun isProfile() : Any? {
    val isProfileObject = UserIsProfileModule()
    val result = isProfileObject.getApiData()
    if(result is UserIsProfileResponseData){
        return result
    }  else if (result is UserError) {
        result.message.forEach {
            Log.d("UserError", it)
        }
        return result
    } else if (result is SocketTimeoutException) {
        return result
    } else if (result is EOFException) {
        return result
    } else if (result is Exception) {
        return result
    } else {
        return result
    }
}

class LoginFragment : Fragment() {
    private val permissionList = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE)

    private val requestMultiplePermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
        results.forEach {
            if(!it.value) {
            }
        }
    }
    var mainActivity: LoginActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val intent = Intent(activity, MainScreenActivity::class.java)
        val binding = LoginFragmentBinding.inflate(inflater, container, false)
        val builder = AlertDialog.Builder(mainActivity)
        requestMultiplePermission.launch(permissionList)

        with(binding) {
            btnRegister.setOnClickListener { mainActivity?.goLoginRegisterFragment() }
            btnLogin.setOnClickListener {
                CoroutineScope(Dispatchers.Default).launch {
                    val result = login(UserLoginData(binding.editId.text.toString().lowercase(), binding.editId.text.toString().lowercase(),
                        binding.editPassword.text.toString()))
                    if(result is UserLoginResponseData){
                        Log.d("Success",result.toString())
                        val res = isProfile()
                        if(res is UserIsProfileResponseData) {
                            if(res.snsResult == 0) {
                                CoroutineScope(Dispatchers.Main).launch {
                                    builder.setMessage("새로운 프로필을 등록해주세요.")
                                builder.setPositiveButton("확인", DialogInterface.OnClickListener{
                                        dialogInterface, i -> mainActivity?.goLoginCreateNewProfileFragment() })
                                builder.show()
                                }
                            } else if(res.snsResult == 99){
                                CoroutineScope(Dispatchers.Main).launch {
                                    builder.setMessage("로그인 되었습니다.")
                                    builder.setPositiveButton("확인", DialogInterface.OnClickListener{
                                            dialogInterface, i -> mainActivity?.startActivity(intent) })
                                    builder.show()
                                }
                            }
                        } else {
                        }
                    } else {
                        CoroutineScope(Dispatchers.Main).launch {
                            builder.setMessage("아이디, 비밀번호를 다시 확인하세요")
                            builder.setPositiveButton("확인", DialogInterface.OnClickListener{
                                    dialogInterface, i -> })
                            builder.show()
                        }
                    }
                }


            }
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as LoginActivity
    }

    override fun onResume() {
        mainActivity?.setTitle("로그인")
        super.onResume()
    }
}