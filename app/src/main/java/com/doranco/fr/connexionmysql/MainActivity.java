package com.doranco.fr.connexionmysql;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    int success;
    JSONParser jsonParser = new JSONParser();
    EditText inputName;
    EditText inputPrice;

    // url du web service de l'ajout

    private static String url_create = "http://192.168.0.33/ajout.php";

    private static final String TAG_SUCCESS = "success";

    @Override



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inputName = (EditText) findViewById(R.id.editText);
        inputPrice = (EditText) findViewById(R.id.editText2);



        Button btnCreateProduct = (Button) findViewById(R.id.btn_ajouter);



        btnCreateProduct.setOnClickListener(

                new View.OnClickListener()

                {

                    @Override

                    public void onClick(View view) {


                        new CreateNewTree().execute();
                    }
                });
    }
    class CreateNewTree extends AsyncTask<String,Void,String>
    {


        @Override

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Insertion..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            String name = inputName.getText().toString();
            String nat = inputPrice.getText().toString();
            List params = new ArrayList();
            params.add(new BasicNameValuePair("nom", name));
            params.add(new BasicNameValuePair("nature", nat));
            JSONObject json = jsonParser.makeHttpRequest(url_create,
                    "GET", params);


            Log.d("Create Response", json.toString());

            // check for success tag

            try {
                success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // todo
                } else {
                    // failed to create

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {

            pDialog.dismiss();
            if (success==1)
            {
                Toast.makeText(MainActivity.this,"Inserted", Toast.LENGTH_LONG).show();
            }
        }

    }
}

