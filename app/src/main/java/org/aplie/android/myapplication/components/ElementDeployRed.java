package org.aplie.android.myapplication.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.aplie.android.myapplication.R;

public class ElementDeployRed extends LinearLayout {
    private final Context context;
    private boolean isDesplegado;
    private ImageView image;

    public ElementDeployRed(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context  = context;
        inicialize();
    }

    public ElementDeployRed(Context context) {
        super(context);
        this.context  = context;
        inicialize();
    }

    private void inicialize() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.deploy_red, this, true);
        this.image = (ImageView) findViewById(R.id.image);
        this.isDesplegado = false;
    }

    public boolean isDesplegado() {
        return this.isDesplegado;
    }

    public void changeMode() {
        if(this.isDesplegado){
            this.isDesplegado = false;
            this.image.setImageDrawable(getResources().getDrawable(R.drawable.undeploy_red));
        }else{
            this.isDesplegado = true;
            this.image.setImageDrawable(getResources().getDrawable(R.drawable.deploy_red));
        }
    }
}
