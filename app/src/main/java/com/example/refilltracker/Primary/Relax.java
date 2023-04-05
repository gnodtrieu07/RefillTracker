package com.example.refilltracker.Primary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.refilltracker.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

public class Relax extends AppCompatActivity{
    Button b_rock,b_scissor,b_paper;
    TextView tv_Score;
    ImageView iv_ComputerChoice, iv_HumanChoice;

    int HumanScore, ComputerScore = 0;

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relax);
        b_rock = findViewById(R.id.bRock);
        b_paper = findViewById(R.id.bPaper);
        b_scissor = findViewById(R.id.bScissor);

        tv_Score = findViewById(R.id.tvScore);

        iv_ComputerChoice = findViewById(R.id.ivComputer);
        iv_HumanChoice = findViewById(R.id.ivCurrent);

        b_rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_HumanChoice.setImageResource(R.drawable.stone);
                String message = play_turn("Rock");
                tv_Score.setText("Score: Human-> "+ Integer.toString(HumanScore)+" Computer-> "+Integer.toString(ComputerScore));
            }
        });

        b_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_HumanChoice.setImageResource(R.drawable.paper);
                String message = play_turn("Paper");
                tv_Score.setText("Score: Human-> "+ Integer.toString(HumanScore)+" Computer-> "+Integer.toString(ComputerScore));
            }
        });

        b_scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_HumanChoice.setImageResource(R.drawable.scissor);
                String message = play_turn("Scissor");
                tv_Score.setText("Score: Human-> "+ Integer.toString(HumanScore)+" Computer-> "+Integer.toString(ComputerScore));
            }
        });
    }

    public String play_turn(String player_choice){
        String comp_choice = "";
        Random r = new Random();

        // choose 1 2 or 3
        int comp_number = r.nextInt(3)+1;

        if (comp_number == 1){
            comp_choice = "Rock";
        }
        else if (comp_number == 2){
            comp_choice = "Paper";
        }
        else if (comp_number == 3){
            comp_choice = "Scissor";
        }
        // set the computer image based on the choice
        if (comp_choice == "Rock"){
            iv_ComputerChoice.setImageResource(R.drawable.stone);
        }
        else if (comp_choice == "Paper"){
            iv_ComputerChoice.setImageResource(R.drawable.paper);
        }
        else if (comp_choice == "Scissor"){
            iv_ComputerChoice.setImageResource(R.drawable.scissor);
        }

        // to determine who will win
        if (comp_choice == player_choice){
            return "Draw: Nobody Won.";
        }
        else if (player_choice == "Rock" && comp_choice == "Scissor"){
            HumanScore++;
            return "Rock crushes Scissor. You Win!";
        }
        else if (player_choice == "Rock" && comp_choice == "Paper"){
            ComputerScore++;
            return "Paper Cover Rock. Computer Win!";
        }
        else if (player_choice == "Scissor" && comp_choice == "Rock"){
            ComputerScore++;
            return "Rock crushes Scissor. Computer Win!";
        }
        else if (player_choice == "Scissor" && comp_choice == "Paper"){
            HumanScore++;
            return "Scissor cuts Paper. You Win!";
        }
        else if (player_choice == "Paper" && comp_choice == "Rock"){
            HumanScore++;
            return "Paper Cover Rock. You Win!";
        }
        else if (player_choice == "Paper" && comp_choice == "Scissor"){
            ComputerScore++;
            return "Scissor cuts Paper. Computer Win!";
        }
        else
            return "Not Sure";

    }

}
