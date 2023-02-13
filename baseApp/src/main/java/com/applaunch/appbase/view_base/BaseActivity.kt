package com.applaunch.appbase.view_base

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.applaunch.appbase.R
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.listner_base.showShimmer
import com.applaunch.appbase.utils_base.*
import com.applaunch.appbase.utils_base.session.SessionManager
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseActivity<VM : BaseViewModel<*>, VB : ViewDataBinding> : AppCompatActivity(),
    BaseRepoListener {

    protected abstract val mViewModel: BaseViewModel<*>

    @get:LayoutRes
    abstract val layoutId: Int?

    abstract val headerData: Triple<String, String, String>

    abstract fun onInitialize()

    abstract fun subscribeObserver()

    lateinit var iBaseRepoListener: BaseRepoListener
    protected lateinit var mViewBinding: VB

    private var mParentView: View? = null

    fun getBaseRepositoryListener(): BaseRepoListener = iBaseRepoListener

    val progressBar: CustomProgressBar by lazy {
        CustomProgressBar()
    }

    val sessionManager: SessionManager by lazy {
        SessionManager(this)
    }

    private val baseCodeSnippet: BaseCodeSnippet by lazy {
        BaseCodeSnippet(this)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        mParentView = window.decorView.findViewById(android.R.id.content)
        return super.onCreateView(name, context, attrs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppUpdate.checkForUpdates(this)

        this@BaseActivity.layoutId?.let { layoutId ->
            mViewBinding = DataBindingUtil.setContentView(this, layoutId)
        }

        iBaseRepoListener = this@BaseActivity

        lifecycleScope.launch {
            async { subscribeObserver() }.await()
            mViewModel.onInitialized(intent.extras)
            onInitialize()
        }
    }

    override fun showLoader() = showProgressBar(progressBar)
    override fun hideLoader() = dismissProgressBar(progressBar)
    override fun shimmer(isLoading: Boolean, shimmerType: String) {
        showShimmer?.invoke(isLoading, shimmerType)
    }

    override fun showMessage(message: String) = toastMessage(message)
    override fun showErrorMessage(message: String) = toastMessage(message)
    override fun getBearerToken(): String = this@BaseActivity.headerData.first
    override fun getApiKey(): String = this@BaseActivity.headerData.second
    override fun getAppVersion(): String = this@BaseActivity.headerData.third

    override fun isNetworkConnected(): Boolean {
        val isConnected = NetworkConnection(this).hasNetworkConnection()
        if (isConnected.not()) {
            showMessage(getString(R.string.not_internet_connection))
            Print.log(getString(R.string.not_internet_connection))
        }
        return isConnected
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            mViewBinding.root.hideKeyboard()
        }
        return super.dispatchTouchEvent(ev)
    }
}