package activity.ocrtest;

import com.googlecode.tesseract.android.TessBaseAPI;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Toast;


public class OcrActivity extends ActionBarActivity {

	private Button imgread, extract;
	private ImageView imageview;
	private Bitmap image;
	private TextView result;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);
        
        imgread = (Button)findViewById(R.id.imgread);
        extract = (Button)findViewById(R.id.extract);
        
        imageview = (ImageView)findViewById(R.id.imageview);
        
        result = (TextView)findViewById(R.id.result);
        
        imgread.setOnClickListener(new View.OnClickListener() {
        	@SuppressLint("SdCardPath") public void onClick(View v) {
        		
        		String bmpPath = "/mnt/sdcard/number.bmp";
        		BitmapFactory.Options options = new BitmapFactory.Options();
        		options.inSampleSize = 2;
        		image = BitmapFactory.decodeFile(bmpPath, options);
        		imageview.setImageBitmap(image);
        	}
        });
        extract.setOnClickListener(new View.OnClickListener() {
        	@SuppressLint("SdCardPath") public void onClick(View v) {
        		String TESSBASE_PATH = "/mnt/sdcard/tesseract/";
        	    String DEFAULT_LANGUAGE = "eng";
        	    TessBaseAPI baseApi = new TessBaseAPI();
        	    baseApi.init(TESSBASE_PATH, DEFAULT_LANGUAGE);
                baseApi.setImage(image);
                //根据Init的语言，获得ocr后的字符串
                String text= baseApi.getUTF8Text();
                result.setText("识别结果：" + text);
                /*
                Toast.makeText(getApplicationContext(), text,
                	     Toast.LENGTH_LONG).show();
                */
                baseApi.end();
        	}
        });
        
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ocr, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
