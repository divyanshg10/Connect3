package com.dcodestar.connect3;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0:null 1:yellow 2:red
    int board[]={0,0,0,0,0,0,0,0,0};
    final int win[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean yellow=true;
    boolean isPlaying=true;
    int squareleft=9;

    public void showcoin(View view){
        ImageView image=(ImageView)view;
        int pos=Integer.parseInt(image.getTag().toString());
        if(board[pos]==0&&isPlaying){
            if(yellow){
                image.setImageResource(R.drawable.yellow);
                board[pos]=1;
            }else{
                image.setImageResource(R.drawable.red);
                board[pos]=2;
            }
            squareleft--;
            image.setScaleX(0);
            image.setScaleY(0);
            image.animate().scaleX(1).scaleY(1).rotationBy(360).setDuration(500);
            yellow=!yellow;
            if(squareleft==0){
                TextView messageView=findViewById(R.id.messageview);
                Button buttonpPlay=findViewById(R.id.buttonplay);
                messageView.setVisibility(View.VISIBLE);
                buttonpPlay.setVisibility(View.VISIBLE);
                messageView.setText("Game Draw");
                messageView.setTextColor(Color.GREEN);
                isPlaying=false;
            }
            for(int[]a:win){
                if(board[a[0]]==board[a[1]]&&board[a[1]]==board[a[2]]&&board[a[0]]!=0){
                    String message=null;
                    TextView messageView=findViewById(R.id.messageview);
                    Button buttonpPlay=findViewById(R.id.buttonplay);
                    if(yellow){
                        message="Red has Won";
                        messageView.setTextColor(Color.RED);
                    }else{
                        messageView.setTextColor(Color.YELLOW);
                        message="Yellow has Won";
                    }

                    messageView.setVisibility(View.VISIBLE);
                    buttonpPlay.setVisibility(View.VISIBLE);
                    messageView.setText(message);
                    isPlaying=false;
                    break;
                }
            }
        }else if(isPlaying){
            image.animate().rotationBy(360).setDuration(500);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View.OnClickListener buttonPlayPressed=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yellow=true;
                isPlaying=true;
                squareleft=9;
                GridLayout gridLayout=findViewById(R.id.board);
                for(int i=0;i<board.length;i++){
                    board[i]=0;
                }
                ImageView image;
                for(int i=0;i<gridLayout.getChildCount();i++){
                    image=(ImageView)gridLayout.getChildAt(i);
                    image.setImageDrawable(null);
                }
                TextView textView=findViewById(R.id.messageview);
                textView.setVisibility(View.INVISIBLE);
                v.setVisibility(View.INVISIBLE);
            }
        };
        Button buttonPlay=findViewById(R.id.buttonplay);
        buttonPlay.setOnClickListener(buttonPlayPressed);
    }
}
