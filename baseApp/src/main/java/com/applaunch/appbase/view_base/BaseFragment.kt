package com.applaunch.appbase.view_base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.applaunch.appbase.R
import com.applaunch.appbase.listner_base.networkConnected
import com.applaunch.appbase.listner_base.unAuthorizeUser
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.listner_base.showShimmer
import com.applaunch.appbase.utils_base.*
import com.applaunch.appbase.utils_base.session.SessionManager
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment<VM : BaseViewModel<*>, VB : ViewDataBinding> : Fragment(),
    BaseRepoListener {

    protected abstract val mViewModel: BaseViewModel<*>

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun onFragmentCreated(isBindingExist: Boolean)

    abstract fun subscribeObservers()

    lateinit var mViewBinding: VB

    var isBindingExist: Boolean = false


    lateinit var iBaseRepoListener: BaseRepoListener
    lateinit var ACCESSTOKAN: String

    private val progressBar: CustomProgressBar by lazy {
        CustomProgressBar()
    }

    val sessionManager: SessionManager by lazy {
        SessionManager(requireContext())
    }

    val baseCodeSnippet: BaseCodeSnippet by lazy {
        BaseCodeSnippet(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        isBindingExist = ::mViewBinding.isInitialized

        if (::mViewBinding.isInitialized.not()) {
            mViewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            mViewBinding.lifecycleOwner = viewLifecycleOwner
        }
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            async {
                subscribeObservers()
            }.await()

            iBaseRepoListener = this@BaseFragment

            mViewModel.onInitialized(arguments)

            onFragmentCreated(isBindingExist)

            initLoader()
            ACCESSTOKAN =
                sessionManager.getPreference(stringPreferencesKey("accessToken"), "").first()
        }
    }

    private fun initLoader() {
        mViewModel.baseLiveData.observe(viewLifecycleOwner) {
            when (it.first) {
                BaseConstants.BaseKeys.SHOW_LOADER -> showLoader()
                BaseConstants.BaseKeys.SHOW_LOADER_MESSAGE -> showMessage(it.second as String)
                BaseConstants.BaseKeys.HIDE_LOADER -> hideLoader()
                BaseConstants.BaseKeys.SHOW_MESSAGE -> showMessage(it.second as String)
            }
        }
    }

    override fun isNetworkConnected(): Boolean {
        val isConnected = NetworkConnection(requireContext()).hasNetworkConnection()
        networkConnected?.invoke(isConnected)
        if (isConnected.not()) {
            Print.log(getString(R.string.not_internet_connection))
        }
        return isConnected
    }

    override fun showLoader() = showProgressBar(progressBar, "Loading....")
    override fun hideLoader() = dismissProgressBar(progressBar)

    override fun shimmer(isLoading:Boolean,shimmerType:String) {
        showShimmer?.invoke(isLoading,shimmerType)
    }

    override fun showErrorMessage(message: String) {
        if (message.equalIgnore("Access denied")) {
            unAuthorizeUser?.invoke(message)
        }
    }

    override fun showMessage(message: String) = toastMessage(message)

    override fun getBearerToken(): String {
        Print.log(" Base Fragment  App Token ${ACCESSTOKAN} \n API KEY ${getApiKey()}")
        return ACCESSTOKAN
    }

    override fun getApiKey(): String = (context as BaseActivity<*, *>).headerData.second

    override fun getAppVersion(): String = (context as BaseActivity<*, *>).headerData.third
}