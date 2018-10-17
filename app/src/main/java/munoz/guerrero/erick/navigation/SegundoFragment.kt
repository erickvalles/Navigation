package munoz.guerrero.erick.navigation


import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_segundo.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SegundoFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SegundoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val vistaRaiz = inflater.inflate(R.layout.fragment_segundo, container, false)
//instanciamos los elementos
//variable  nombre            buscarporID(id (xml))        casteo a objeto
        val web_view = vistaRaiz.findViewById(R.id.web_view) as WebView
        val btnCargar = vistaRaiz.findViewById<Button>(R.id.btnCargar)
        val progressBar = vistaRaiz.findViewById(R.id.progreso) as ProgressBar
        val etUrl = vistaRaiz.findViewById(R.id.etDireccion) as EditText

        //obtener y editar los ajustes del webview
        val settings = web_view.settings //lo obtenemos
        settings.javaScriptEnabled = true //habilitamos el soporte de JavaScript
        //webview client
        web_view.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
               Toast.makeText(context,"Se comenzó la carga",Toast.LENGTH_LONG).show()
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
               Toast.makeText(context,"${view!!.title}",Toast.LENGTH_SHORT).show()
                super.onPageFinished(view, url)
            }
        }
        web_view.webChromeClient = object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                progressBar.progress = newProgress
                super.onProgressChanged(view, newProgress)
            }
        }

        btnCargar.setOnClickListener{
            var url = etUrl.text.toString().trim()
            if (!url.isEmpty()){
                url = "https://"+url
                web_view.loadUrl(url)
            }
        }




        return vistaRaiz
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SegundoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SegundoFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
