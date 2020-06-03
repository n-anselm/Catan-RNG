package com.anselmdevelopment.catanrng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appizona.yehiahd.fastsave.FastSave;

import java.util.ArrayList;
import java.util.Random;

import static com.anselmdevelopment.catanrng.SettingsActivity.ANIMATE;
//import static com.anselmdevelopment.catanrng.SettingsActivity.DUPLICATE;
import static com.anselmdevelopment.catanrng.SettingsActivity.EXCLUDE7;
import static com.anselmdevelopment.catanrng.SettingsActivity.RADIO1;
import static com.anselmdevelopment.catanrng.SettingsActivity.RADIO2;
import static com.anselmdevelopment.catanrng.SettingsActivity.RADIO3;
import static com.anselmdevelopment.catanrng.SettingsActivity.VIBRATE;

public class MainActivity extends AppCompatActivity {

    int number;
    int animation;
    int two;
    int three;
    int four;
    int five;
    int six;
    int seven;
    int eight;
    int nine;
    int ten;
    int eleven;
    int twelve;
    public static final ArrayList<String> history = new ArrayList<>();
    public static String hist = null;
    public boolean menuVisible;
    public boolean historyVisible;
    public boolean isFirstRun; // This is used for the generateAiNumber method
    private TextView tvRandomNumber;
    private TextView tvGenerateButton;
    ConstraintLayout constraintLayoutMenu;
    ConstraintLayout constraintLayoutHistory;

    @Override
    public void onBackPressed() {
        if (menuVisible) {
            constraintLayoutMenu.setVisibility(View.GONE);
            menuVisible = false;
        } else if (historyVisible) {
            constraintLayoutHistory.setVisibility(View.GONE);
            historyVisible = false;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FastSave.init(getApplicationContext()); // Initialize FastSave - Reference: "https://github.com/yehiahd/FastSave-Android"
        setContentView(R.layout.activity_main);

        tvRandomNumber = findViewById(R.id.random_number);
        tvGenerateButton = findViewById(R.id.generate_button);
        constraintLayoutHistory = findViewById(R.id.constraintlayout_history);
        animation = 1;
        menuVisible = false;
        historyVisible = false;
        isFirstRun = true;

        final ImageView optionsMenu = findViewById(R.id.options_menu);
        optionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                constraintLayoutMenu = findViewById(R.id.constraintlayout_menu);
                constraintLayoutMenu.setVisibility(View.VISIBLE);
                menuVisible = true;

                TextView tvgameOverView = findViewById(R.id.tv_gameoverview);
                TextView tvhistory = findViewById(R.id.tv_history);
                TextView tvsettings = findViewById(R.id.tv_settings);
                TextView tvabout = findViewById(R.id.tv_about);

                LinearLayout topll = findViewById(R.id.linearlayout_top);
                LinearLayout leftll = findViewById(R.id.linearlayout_left);
                LinearLayout rightll = findViewById(R.id.linearlayout_right);
                LinearLayout bottomll = findViewById(R.id.linearlayout_bottom);

                tvgameOverView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, GameOverviewActivity.class));
                        hideMenuWithDelay();
                    }
                });

                tvhistory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideMenu();

                        final TextView tvHistoryNumbers = findViewById(R.id.tv_infotext);
                        TextView ok = findViewById(R.id.tv_ok);
                        TextView clear = findViewById(R.id.tv_clear);

                        LinearLayout top2 = findViewById(R.id.linearlayout_top2);
                        LinearLayout left2 = findViewById(R.id.linearlayout_left2);
                        LinearLayout right2 = findViewById(R.id.linearlayout_right2);
                        LinearLayout bottom2 = findViewById(R.id.linearlayout_bottom2);

                        if (history.isEmpty()) {
                            hist = "No history";
                        } else {
                            /* Put the values of the ArrayList history into the String hist and replace
                            the [ and ] with nothing so they don't appear in the history dialog */
                            hist = history.toString().replace("[", "").replace("]", "");
                        }

                        tvHistoryNumbers.setText(hist);
                        constraintLayoutHistory.setVisibility(View.VISIBLE);
                        historyVisible = true;

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int num = 1;
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        constraintLayoutHistory.setVisibility(View.GONE);
                                    }
                                }, num * 100);
                            }
                        });

                        clear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                history.clear();
                                tvHistoryNumbers.setText("No history");
                            }
                        });

                        top2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                hideHistory();
                            }
                        });

                        left2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                hideHistory();
                            }
                        });

                        right2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                hideHistory();
                            }
                        });

                        bottom2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                hideHistory();
                            }
                        });
                    }
                });

                tvsettings.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        hideMenuWithDelay();
                    }
                });

                tvabout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        hideMenuWithDelay();
                    }
                });

                /** Set onClick listeners on the areas around the menu so that when they are clicked
                 * the menu disappears
                 */
                topll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideMenu();
                    }
                });

                leftll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideMenu();
                    }
                });

                rightll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideMenu();
                    }
                });

                bottomll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideMenu();
                    }
                });
            }
        });

        // Set onClickListener on the generate button
        tvGenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber = processRandomNumber();
                animateAndSetText(randomNumber);
            }
        });
    }

    public void hideMenu() {
        constraintLayoutMenu.setVisibility(View.GONE);
        menuVisible = false;
    }

    public void hideMenuWithDelay() {
        final ConstraintLayout constraintLayoutMenu = findViewById(R.id.constraintlayout_menu);
        int num = 1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                constraintLayoutMenu.setVisibility(View.GONE);
            }
        }, num * 250);
        menuVisible = false;
    }

    public void hideHistory() {
        constraintLayoutHistory.setVisibility(View.GONE);
        historyVisible = false;
    }

    public void animateAndSetText(final int randomNumber) {
        int delay = 1;
        // If the animate setting in Settings is turned on, then animate the numbers
        if (FastSave.getInstance().getBoolean(ANIMATE, true)) {
            if (animation == 1) {
                tvRandomNumber.setText("5");
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("2");
                    }
                }, delay + 75);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("10");
                    }
                }, delay + 150);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("1");
                    }
                }, delay + 225);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("4");
                    }
                }, delay + 300);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("12");
                    }
                }, delay + 375);

                // Display the value of the actual random number
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText(Integer.toString(randomNumber));
                    }
                }, delay + 450);
                animation = 2;

            } else if (animation == 2) {
                tvRandomNumber.setText("11");
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("6");
                    }
                }, delay + 75);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("9");
                    }
                }, delay + 150);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("7");
                    }
                }, delay + 225);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("1");
                    }
                }, delay + 300);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("3");
                    }
                }, delay + 375);

                // Display the value of the actual random number
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText(Integer.toString(randomNumber));
                    }
                }, delay + 450);
                animation = 3;

            } else if (animation == 3) {
                tvRandomNumber.setText("7");
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("8");
                    }
                }, delay + 75);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("12");
                    }
                }, delay + 150);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("4");
                    }
                }, delay + 225);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("6");
                    }
                }, delay + 300);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText("9");
                    }
                }, delay + 375);

                // Display the value of the actual random number
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvRandomNumber.setText(Integer.toString(randomNumber));
                    }
                }, delay + 450);
                animation = 1;
            }
        } else {
            tvRandomNumber.setText(Integer.toString(randomNumber));
        }
    }

    // Generate a random number with AI (Artificial Intelligence)
    public int generateAiNumber() {
        int rn = generateRn2to12();

        if (isFirstRun) {
            two = 1;
            three = 1;
            four = 1;
            five = 1;
            six = 2;
            seven = 1;
            eight = 2;
            nine = 2;
            ten = 1;
            eleven = 1;
            twelve = 1;

//            switch (rn) {
//                case 2:
//                    two = 1;
//                    break;
//                case 3:
//                    three = 1;
//                    break;
//                case 4:
//                    four = 1;
//                    break;
//                case 5:
//                    five = 1;
//                    break;
//                case 6:
//                    six = 2;
//                    break;
//                case 7:
//                    seven = 1;
//                    break;
//                case 8:
//                    eight = 2;
//                    break;
//                case 9:
//                    nine = 2;
//                    break;
//                case 10:
//                    ten = 1;
//                    break;
//                case 11:
//                    eleven = 1;
//                    break;
//                case 12:
//                    twelve = 1;
//            }

//            if (rn == 2) {
//                two += 1;
//            } else if (rn == 3) {
//                three += 1;
//            } else if (rn == 4) {
//                four += 1;
//            } else if (rn == 5) {
//                five += 1;
//            } else if (rn == 6) {
//                six += 1;
//            } else if (rn == 7) {
//                seven += 1;
//            } else if (rn == 8) {
//                eight += 1;
//            } else if (rn == 9) {
//                nine += 1;
//            } else if (rn == 10) {
//                ten += 1;
//            } else if (rn == 11) {
//                eleven += 1;
//            } else if (rn == 12) {
//                twelve += 1;
//            }
            isFirstRun = false;
        } else {
            if (rn == 2 && two < 6) {
                rn = 0;
            } else if (rn == 3 && three < 5) {
                rn = 0;
            } else if (rn == 4 && four < 4) {
                rn = 0;
            } else if (rn == 5 && five < 3) {
                rn = 0;
            } else if (rn == 6 && six < 2) {
                rn = 0;
            } else if (rn == 7 && seven < 1) {
                rn = 0;
            } else if (rn == 8 && eight < 2) {
                rn = 0;
            } else if (rn == 9 && nine < 3) {
                rn = 0;
            } else if (rn == 10 && ten < 4) {
                rn = 0;
            } else if (rn == 11 && eleven < 5) {
                rn = 0;
            } else if (rn == 12 && twelve < 6) {
                rn = 0;
            }
            if (rn == 2) {
                two = 0;
                three += 1;
                four += 1;
                five += 1;
                six += 1;
                seven += 1;
                eight += 1;
                nine += 1;
                ten += 1;
                eleven += 1;
                twelve += 1;
            } else if (rn == 3) {
                two += 1;
                three = 0;
                four += 1;
                five += 1;
                six += 1;
                seven += 1;
                eight += 1;
                nine += 1;
                ten += 1;
                eleven += 1;
                twelve += 1;
            } else if (rn == 4) {
                two += 1;
                three += 1;
                four += 4;
                five += 1;
                six += 1;
                seven += 1;
                eight += 1;
                nine += 1;
                ten += 1;
                eleven += 1;
                twelve += 1;
            } else if (rn == 5) {
                two += 1;
                three += 1;
                four += 1;
                five = 0;
                six += 1;
                seven += 1;
                eight += 1;
                nine += 1;
                ten += 1;
                eleven += 1;
                twelve += 1;
            } else if (rn == 6) {
                two = 0;
                three += 1;
                four += 1;
                five += 1;
                six = 0;
                seven += 1;
                eight += 1;
                nine += 1;
                ten += 1;
                eleven += 1;
                twelve += 1;
            } else if (rn == 7) {
                two += 1;
                three += 1;
                four += 1;
                five += 1;
                six += 1;
                seven = 0;
                eight += 1;
                nine += 1;
                ten += 1;
                eleven += 1;
                twelve += 1;
            } else if (rn == 8) {
                two += 1;
                three += 1;
                four += 1;
                five += 1;
                six += 1;
                seven += 1;
                eight = 0;
                nine += 1;
                ten += 1;
                eleven += 1;
                twelve += 1;
            } else if (rn == 9) {
                two += 1;
                three += 1;
                four += 1;
                five += 1;
                six += 1;
                seven += 1;
                eight += 1;
                nine = 0;
                ten += 1;
                eleven += 1;
                twelve += 1;
            } else if (rn == 10) {
                two += 1;
                three += 1;
                four += 1;
                five += 1;
                six += 1;
                seven += 1;
                eight += 1;
                nine += 1;
                ten = 0;
                eleven += 1;
                twelve += 1;
            } else if (rn == 11) {
                two += 1;
                three += 1;
                four += 1;
                five += 1;
                six += 1;
                seven += 1;
                eight += 1;
                nine += 1;
                ten += 1;
                eleven = 0;
                twelve += 1;
            } else if (rn == 12) {
                two += 1;
                three += 1;
                four += 1;
                five += 1;
                six += 1;
                seven += 1;
                eight += 1;
                nine += 1;
                ten += 1;
                eleven += 1;
                twelve = 0;
            }
        }

        return rn;
    }

    // Generate a random number according to method 2
    public int generateMethod2() {
        int rn1 = generateRn1to6();
        if (rn1 == number) { // 1st filter
            int rn2 = generateRn1to6();
            if (rn2 == number) { // 2nd filter
                number = generateRn1to6();
            } else {
                number = rn2;
            }
        } else {
            number = rn1;
        }
        return number;
    }

    // Generate a random number according to method 3
    public int generateMethod3() {
        int rn = generateRn2to12();
        if (rn == number) { // 1st filter
            int rn2 = generateRn2to12();
            if (rn2 == number) { // 2nd filter
                number = generateRn2to12();
            } else {
                number = rn2;
            }
        } else {
            number = rn;
        }
        return number;
    }

    // Generate a random number according to generate method 1
    public int generateRn1to6() {
        int min = 1;
        int max = 6;
        Random random1 = new Random();
        int rn1 = random1.nextInt(max - min + 1) + min;
        Random random2 = new Random();
        int rn2 = random2.nextInt(max - min + 1) + min;
        int randomNumber = rn1 + rn2;
        return randomNumber;
    }

    // Generate a random number according to generate method 2
    public int generateRn2to12() {
        int min = 2;
        int max = 12;
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
//        history.add(String.valueOf(randomNumber));
        return randomNumber;
    }

    public int processRandomNumber() {
        // If the vibrate setting in Settings is turned on, then vibrate
        if (FastSave.getInstance().getBoolean(VIBRATE, true)) {
            vibrate();
        }

        int randomNumber = 0;

        if (FastSave.getInstance().getBoolean(RADIO1, true)) {
            for (int i = 0; i < 1; i = i + 0) {
                randomNumber = generateAiNumber();
                i = randomNumber;
            }
        } else if (FastSave.getInstance().getBoolean(RADIO2, false)) {
            randomNumber = generateMethod2();
        } else if (FastSave.getInstance().getBoolean(RADIO3, false)) {
            randomNumber = generateMethod3();
        }

        return randomNumber;
    }

//    public void setRandomNumberText(int i) {
//        final int x = i;
//        if (FastSave.getInstance().getBoolean(EXCLUDE7, false) && x == 7) {
//            processRandomNumber();
//        } else {
//            if (FastSave.getInstance().getBoolean(ANIMATE, true)) {
//                int delay = 1;
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//                        tvRandomNumber.setText(Integer.toString(x));
//                    }
//                }, delay + 500);
//            } else {
//                tvRandomNumber.setText(Integer.toString(x));
//            }
//        }
//    }

    public void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 70 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(70, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(70); // Deprecated in API 26
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    // Hides the status bar
    private void hideSystemUI() {
        // Enables regular immersive mode.
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
