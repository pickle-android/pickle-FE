//package com.example.pickle;
//
//import android.Manifest;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.tmapmobility.tmap.tmapsdk.ui.fragment.NavigationFragment;
//import com.tmapmobility.tmap.tmapsdk.ui.util.TmapUISDK;
//
//public class MainActivity extends AppCompatActivity {
//    private static final int REQUEST_CODE_PERMISSIONS = 1001;
//    private static final String TAG = "MainActivity";
//    private static final String API_KEY = "LTGGNBC5Ps3xITXbxkqULSFfAyeH4nF3lEX0ywOh";
//
// "5DFtawLtCh1TYSPBAJUHtIunYzs4nDC5nBuqD7X7"  "iFShl9nkS93HC6R58zoX3IFsSVK4Kd81sth4hOoh" "BkdiLZtbKe5gRKWTFZO221HSFLfvpYu04xN7Fkou"



//    private static final String CLIENT_ID ="minson0514@naver.com"; //"wowow716@naver.com"
//    private static final String USER_KEY = "";
//    private static final String DEVICE_KEY = "RT9LIUo/Pz97Ij9SQWMNCg=="; // "e5lLAqYI5CF5lyAboD1W+AUTHBE="
//
//    private NavigationFragment navigationFragment;
//    private FragmentManager fragmentManager;
//    private FragmentTransaction transaction;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        Button buttonOpenSubActivity = findViewById(R.id.button_open_subactivity);
//        buttonOpenSubActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SubActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        // Check and request permissions
//        if (!hasPermissions()) {
//            requestPermissions();
//        } else {
//            initializeTmap();
//        }
//    }
//
//    private boolean hasPermissions() {
//        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private void requestPermissions() {
//        ActivityCompat.requestPermissions(this,
//                new String[]{
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.INTERNET
//                },
//                REQUEST_CODE_PERMISSIONS);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE_PERMISSIONS) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permissions granted, initialize Tmap
//                initializeTmap();
//            } else {
//                // Permissions not granted, show a message to the user
//                Toast.makeText(this, "Permissions not granted", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void initializeTmap() {
//        fragmentManager = getSupportFragmentManager();
//        navigationFragment = TmapUISDK.Companion.getFragment();
//        transaction = fragmentManager.beginTransaction();
//        transaction.add(R.id.tmapUILayout, navigationFragment);
//        transaction.commitAllowingStateLoss();
//
//        TmapUISDK.Companion.initialize(this, CLIENT_ID, API_KEY, USER_KEY, DEVICE_KEY, new TmapUISDK.InitializeListener() {
//            @Override
//            public void onSuccess() {
//                Log.e(TAG, "success initialize");
//            }
//
//            @Override
//            public void onFail(int i, @Nullable String s) {
//                Toast.makeText(MainActivity.this, i + "::" + s, Toast.LENGTH_SHORT).show();
//                Log.e(TAG, "onFail " + i + " :: " + s);
//            }
//
//            @Override
//            public void savedRouteInfoExists(@Nullable String dest) {
//                Log.e(TAG, "목적지 : " + dest);
//                if (dest != null) {
//                    //이전 목적지가 존재하면 이어서 안내 여부 Dialog 띄움
//                    showDialogContinueRoute(dest);
//                }
//            }
//
//            private void showDialogContinueRoute(String dest) {
//                new AlertDialog.Builder(MainActivity.this)
//                        .setTitle("이전 경로 안내")
//                        .setMessage("이전에 설정된 목적지로 이어서 안내하시겠습니까?\n목적지: " + dest)
//                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // 이어서 안내하는 로직 추가
//                                continueRoute(dest);
//                            }
//                        })
//                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .show();
//            }
//
//            private void continueRoute(String dest) {
//                // 이어서 안내하는 로직 구현
//                Log.e(TAG, "Continuing route to: " + dest);
//            }
//        });
//    }
//}



//
//package com.example.pickle;
//
//import android.Manifest;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.skt.tmap.engine.navigation.SDKManager;
//import com.skt.tmap.engine.navigation.route.data.MapPoint;
//import com.tmapmobility.tmap.tmapsdk.ui.fragment.NavigationFragment;
//import com.tmapmobility.tmap.tmapsdk.ui.util.TmapUISDK;
//import com.skt.tmap.vsm.map.MapEngine;
//
//public class MainActivity extends AppCompatActivity {
//    private static final int REQUEST_CODE_PERMISSIONS = 1001;
//    private static final String TAG = "MainActivity";
//    private static final String API_KEY = "LTGGNBC5Ps3xITXbxkqULSFfAyeH4nF3lEX0ywOh";
//    private static final String CLIENT_ID = "minson0514@naver.com";
//    private static final String USER_KEY = "";
//    private static final String DEVICE_KEY = "RT9LIUo/Pz97Ij9SQWMNCg==";
//
//    private NavigationFragment navigationFragment;
//    private FragmentManager fragmentManager;
//    private FragmentTransaction transaction;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        Button buttonOpenSubActivity = findViewById(R.id.button_open_subactivity);
//        buttonOpenSubActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SubActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        ImageButton buttonCurrentLocation = findViewById(R.id.button_current_location);
//        buttonCurrentLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                moveToCurrentLocation();
//            }
//        });
//
//        // Check and request permissions
//        if (!hasPermissions()) {
//            requestPermissions();
//        } else {
//            initializeTmap();
//        }
//    }
//
//    private boolean hasPermissions() {
//        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private void requestPermissions() {
//        ActivityCompat.requestPermissions(this,
//                new String[]{
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.INTERNET
//                },
//                REQUEST_CODE_PERMISSIONS);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE_PERMISSIONS) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                initializeTmap();
//            } else {
//                Toast.makeText(this, "Permissions not granted", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void initializeTmap() {
//        fragmentManager = getSupportFragmentManager();
//        navigationFragment = TmapUISDK.Companion.getFragment();
//        transaction = fragmentManager.beginTransaction();
//        transaction.add(R.id.tmapUILayout, navigationFragment);
//        transaction.commitAllowingStateLoss();
//
//        TmapUISDK.Companion.initialize(this, CLIENT_ID, API_KEY, USER_KEY, DEVICE_KEY, new TmapUISDK.InitializeListener() {
//            @Override
//            public void onSuccess() {
//                Log.e(TAG, "success initialize");
//            }
//
//            @Override
//            public void onFail(int i, @Nullable String s) {
//                Toast.makeText(MainActivity.this, i + "::" + s, Toast.LENGTH_SHORT).show();
//                Log.e(TAG, "onFail " + i + " :: " + s);
//            }
//
//            @Override
//            public void savedRouteInfoExists(@Nullable String dest) {
//                Log.e(TAG, "목적지 : " + dest);
//                if (dest != null) {
//                    showDialogContinueRoute(dest);
//                }
//            }
//
//            private void showDialogContinueRoute(String dest) {
//                new AlertDialog.Builder(MainActivity.this)
//                        .setTitle("이전 경로 안내")
//                        .setMessage("이전에 설정된 목적지로 이어서 안내하시겠습니까?\n목적지: " + dest)
//                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                continueRoute(dest);
//                            }
//                        })
//                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .show();
//            }
//
//            private void continueRoute(String dest) {
//                Log.e(TAG, "Continuing route to: " + dest);
//            }
//        });
//    }
//
//    private void moveToCurrentLocation() {
//        Location currentLocation = SDKManager.getInstance().getCurrentPosition();
//        if (currentLocation != null) {
//            // 현재 위치로 지도를 이동하는 로직 추가
//            MapEngine mapEngine = navigationFragment.getMapView();
//            if (mapEngine != null) {
//                mapEngine.setMapCenter(currentLocation.getLongitude(), currentLocation.getLatitude(), true);
//            } else {
//                Log.e(TAG, "MapEngine is null");
//            }
//        } else {
//            Log.e(TAG, "Current location is null");
//        }
//    }
//}





//package com.example.pickle;
//
//import android.Manifest;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.google.android.gms.common.api.Status;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.libraries.places.api.Places;
//import com.google.android.libraries.places.api.model.Place;
//import com.google.android.libraries.places.widget.Autocomplete;
//import com.google.android.libraries.places.widget.AutocompleteActivity;
//import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
//import com.skt.tmap.engine.navigation.SDKManager;
//import com.skt.tmap.engine.navigation.route.data.MapPoint;
//import com.tmapmobility.tmap.tmapsdk.ui.fragment.NavigationFragment;
//import com.tmapmobility.tmap.tmapsdk.ui.util.TmapUISDK;
//import com.skt.tmap.vsm.map.MapEngine;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    private static final int REQUEST_CODE_PERMISSIONS = 1001;
//    private static final int SEARCH_REQUEST_CODE = 1003;
//    private static final String TAG = "MainActivity";
//    private static final String API_KEY = "LTGGNBC5Ps3xITXbxkqULSFfAyeH4nF3lEX0ywOh";
//    private static final String CLIENT_ID = "minson0514@naver.com";
//    private static final String USER_KEY = "";
//    private static final String DEVICE_KEY = "RT9LIUo/Pz97Ij9SQWMNCg==";
//
//    private NavigationFragment navigationFragment;
//    private FragmentManager fragmentManager;
//    private FragmentTransaction transaction;
//    private EditText searchEditText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        // Initialize Places
//        if (!Places.isInitialized()) {
//            Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
//        }
//
//        searchEditText = findViewById(R.id.search_edit_text);
//        searchEditText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//                startActivityForResult(intent, SEARCH_REQUEST_CODE);
//            }
//        });
//
//        Button buttonOpenSubActivity = findViewById(R.id.button_open_subactivity);
//        buttonOpenSubActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SubActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        ImageButton buttonCurrentLocation = findViewById(R.id.button_current_location);
//        buttonCurrentLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                moveToCurrentLocation();
//            }
//        });
//
//        // Check and request permissions
//        if (!hasPermissions()) {
//            requestPermissions();
//        } else {
//            initializeTmap();
//        }
//    }
//
//    private boolean hasPermissions() {
//        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private void requestPermissions() {
//        ActivityCompat.requestPermissions(this,
//                new String[]{
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.INTERNET
//                },
//                REQUEST_CODE_PERMISSIONS);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE_PERMISSIONS) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                initializeTmap();
//            } else {
//                Toast.makeText(this, "Permissions not granted", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void initializeTmap() {
//        fragmentManager = getSupportFragmentManager();
//        navigationFragment = TmapUISDK.Companion.getFragment();
//        transaction = fragmentManager.beginTransaction();
//        transaction.add(R.id.tmapUILayout, navigationFragment);
//        transaction.commitAllowingStateLoss();
//
//        TmapUISDK.Companion.initialize(this, CLIENT_ID, API_KEY, USER_KEY, DEVICE_KEY, new TmapUISDK.InitializeListener() {
//            @Override
//            public void onSuccess() {
//                Log.e(TAG, "success initialize");
//            }
//
//            @Override
//            public void onFail(int i, @Nullable String s) {
//                Toast.makeText(MainActivity.this, i + "::" + s, Toast.LENGTH_SHORT).show();
//                Log.e(TAG, "onFail " + i + " :: " + s);
//            }
//
//            @Override
//            public void savedRouteInfoExists(@Nullable String dest) {
//                Log.e(TAG, "목적지 : " + dest);
//                if (dest != null) {
//                    showDialogContinueRoute(dest);
//                }
//            }
//
//            private void showDialogContinueRoute(String dest) {
//                new AlertDialog.Builder(MainActivity.this)
//                        .setTitle("이전 경로 안내")
//                        .setMessage("이전에 설정된 목적지로 이어서 안내하시겠습니까?\n목적지: " + dest)
//                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                continueRoute(dest);
//                            }
//                        })
//                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .show();
//            }
//
//            private void continueRoute(String dest) {
//                Log.e(TAG, "Continuing route to: " + dest);
//            }
//        });
//    }
//
//    private void moveToCurrentLocation() {
//        Location currentLocation = SDKManager.getInstance().getCurrentPosition();
//        if (currentLocation != null) {
//            // 현재 위치로 지도를 이동하는 로직 추가
//            MapEngine mapEngine = navigationFragment.getMapView();
//            if (mapEngine != null) {
//                mapEngine.setMapCenter(currentLocation.getLongitude(), currentLocation.getLatitude(), true);
//            } else {
//                Log.e(TAG, "MapEngine is null");
//            }
//        } else {
//            Log.e(TAG, "Current location is null");
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == SEARCH_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
//            String selectedPlace = data.getStringExtra("selectedPlace");
//            searchEditText.setText(selectedPlace);
//            // 선택된 장소를 사용하여 작업 수행 (예: 지도를 해당 위치로 이동)
//            // moveToPlaceLocation(place.getLatLng()); // 필요시 LatLng을 통해 지도 이동 코드 추가
//        }
//    }
//
//    private void moveToPlaceLocation(LatLng latLng) {
//        if (latLng != null) {
//            MapEngine mapEngine = navigationFragment.getMapView();
//            if (mapEngine != null) {
//                mapEngine.setMapCenter(latLng.longitude, latLng.latitude, true);
//            } else {
//                Log.e(TAG, "MapEngine is null");
//            }
//        } else {
//            Log.e(TAG, "LatLng is null");
//        }
//    }
//}


package com.example.pickle;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.skt.tmap.engine.navigation.SDKManager;
import com.skt.tmap.engine.navigation.route.data.MapPoint;
import com.tmapmobility.tmap.tmapsdk.ui.fragment.NavigationFragment;
import com.tmapmobility.tmap.tmapsdk.ui.util.TmapUISDK;
import com.skt.tmap.vsm.map.MapEngine;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSIONS = 1001;
    private static final int SEARCH_REQUEST_CODE = 1003;
    private static final String TAG = "MainActivity";
    private static final String API_KEY = "LTGGNBC5Ps3xITXbxkqULSFfAyeH4nF3lEX0ywOh";
    private static final String CLIENT_ID = "minson0514@naver.com";
    private static final String USER_KEY = "";
    private static final String DEVICE_KEY = "RT9LIUo/Pz97Ij9SQWMNCg==";

    private NavigationFragment navigationFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Places
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
        }

        searchEditText = findViewById(R.id.search_edit_text);
        searchEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivityForResult(intent, SEARCH_REQUEST_CODE);
            }
        });

        Button buttonOpenSubActivity = findViewById(R.id.button_open_subactivity);
        buttonOpenSubActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });

        ImageButton buttonCurrentLocation = findViewById(R.id.button_current_location);
        buttonCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToCurrentLocation();
            }
        });

        // Check and request permissions
        if (!hasPermissions()) {
            requestPermissions();
        } else {
            initializeTmap();
        }
    }

    private boolean hasPermissions() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                },
                REQUEST_CODE_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeTmap();
            } else {
                Toast.makeText(this, "Permissions not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initializeTmap() {
        fragmentManager = getSupportFragmentManager();
        navigationFragment = TmapUISDK.Companion.getFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.tmapUILayout, navigationFragment);
        transaction.commitAllowingStateLoss();

        TmapUISDK.Companion.initialize(this, CLIENT_ID, API_KEY, USER_KEY, DEVICE_KEY, new TmapUISDK.InitializeListener() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "success initialize");
            }

            @Override
            public void onFail(int i, @Nullable String s) {
                Toast.makeText(MainActivity.this, i + "::" + s, Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFail " + i + " :: " + s);
            }

            @Override
            public void savedRouteInfoExists(@Nullable String dest) {
                Log.e(TAG, "목적지 : " + dest);
                if (dest != null) {
                    showDialogContinueRoute(dest);
                }
            }

            private void showDialogContinueRoute(String dest) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("이전 경로 안내")
                        .setMessage("이전에 설정된 목적지로 이어서 안내하시겠습니까?\n목적지: " + dest)
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                continueRoute(dest);
                            }
                        })
                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

            private void continueRoute(String dest) {
                Log.e(TAG, "Continuing route to: " + dest);
            }
        });
    }

    private void moveToCurrentLocation() {
        Location currentLocation = SDKManager.getInstance().getCurrentPosition();
        if (currentLocation != null) {
            // 현재 위치로 지도를 이동하는 로직 추가
            MapEngine mapEngine = navigationFragment.getMapView();
            if (mapEngine != null) {
                mapEngine.setMapCenter(currentLocation.getLongitude(), currentLocation.getLatitude(), true);
            } else {
                Log.e(TAG, "MapEngine is null");
            }
        } else {
            Log.e(TAG, "Current location is null");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String selectedPlace = data.getStringExtra("selectedPlace");
            searchEditText.setText(selectedPlace);
            // 선택된 장소를 사용하여 작업 수행 (예: 지도를 해당 위치로 이동)
            // moveToPlaceLocation(place.getLatLng()); // 필요시 LatLng을 통해 지도 이동 코드 추가
        }
    }

    private void moveToPlaceLocation(LatLng latLng) {
        if (latLng != null) {
            MapEngine mapEngine = navigationFragment.getMapView();
            if (mapEngine != null) {
                mapEngine.setMapCenter(latLng.longitude, latLng.latitude, true);
            } else {
                Log.e(TAG, "MapEngine is null");
            }
        } else {
            Log.e(TAG, "LatLng is null");
        }
    }
}
