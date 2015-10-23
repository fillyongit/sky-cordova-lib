package com.example.alessio.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.skytree.epub.PageTransition;
import com.skytree.epub.ReflowableControl;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String dataPath = getFilesDir().getAbsolutePath() + "/";

        ReflowableControl rv = new ReflowableControl(this);
        rv.bookCode = 1;
        try {
            InputStream in = getAssets().open("books/Alice.epub");
            //File f = new File(dataPath);
            OutputStream out = new FileOutputStream(getFilesDir() + "/Alice.epub");
            byte buffer[] = new byte[1024];
            int length = 0;
            while((length=in.read(buffer)) > 0) {
                out.write(buffer,0,length);
            }
            out.close();
            in.close();
        }
        catch(Exception ex) {

        }
        rv.setBaseDirectory(dataPath);
        rv.setBookName("Alice.epub");

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        rv.setLayoutParams(params);
        rv.setStartPositionInBook(1);
        rv.useDOMForHighlight(false);
        // If you want to get the license key for commercial use, please email us (skytree21@gmail.com).
        // Without the license key, watermark message will be shown in background.
        rv.setLicenseKey("0000-0000-0000-0000");
        rv.setPageTransition(PageTransition.None);
        rv.setDrawingHighlightOnFront(false);
        // In case that text of page should be extracted, use rv.setExtractText(true),
        // then in onPageMoved event, you can get pageInformation.description for the text of current page.
        rv.setExtractText(false);
        rv.setCustomDrawHighlight(true);
        rv.setCustomDrawCaret(true);
        rv.setFontUnit("px");
        rv.setFingerTractionForSlide(true);

        // make engine not to send any event to iframe
        // if iframe clicked, onIFrameClicked will be fired with source of iframe
        // By Using that source of iframe, you can load the content of iframe in your own webView or another browser.
        rv.setSendingEventsToIFrameEnabled(false);

        // make engine send any event to video(tag) or not
        // if video tag is clicked, onVideoClicked will be fired with source of iframe
        // By Using that source of video, you can load the content of video in your own media controller or another browser.
        rv.setSendingEventsToVideoEnabled(true);

        // make engine send any event to video(tag) or not
        // if video tag is clicked, onVideoClicked will be fired with source of iframe
        // By Using that source of video, you can load the content of video in your own media controller or another browser.
        rv.setSendingEventsToAudioEnabled(true);

        // Since 5.1.0
        // when this value is true, all offsets of highlights will be calculated and sent
        // based on the character offset of text in chapter rather than the offset of each element.
        // the startIndex and endIndex of highlight will be set to 0 (zero)
        rv.setGlobalOffset(true);

        RelativeLayout ePubView = new RelativeLayout(this);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        ePubView.setLayoutParams(rlp);

        //setContentView(R.layout.activity_main);
        setContentView(ePubView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
