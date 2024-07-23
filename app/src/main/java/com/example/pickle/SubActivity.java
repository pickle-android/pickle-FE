//////    경로요청까지 파일

//package com.example.pickle;//package com.example.pickle;
//
//import android.content.Intent;
//import android.location.Location;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.skt.tmap.engine.navigation.SDKManager;
//import com.skt.tmap.engine.navigation.network.ndds.CarOilType;
//import com.skt.tmap.engine.navigation.network.ndds.TollCarType;
//import com.skt.tmap.engine.navigation.route.data.MapPoint;
//import com.skt.tmap.engine.navigation.route.data.WayPoint;
//import com.skt.tmap.vsm.coordinates.VSMCoordinates;
//import com.tmapmobility.tmap.tmapsdk.ui.data.CarOption;
//import com.tmapmobility.tmap.tmapsdk.ui.fragment.NavigationFragment;
//import com.tmapmobility.tmap.tmapsdk.ui.util.TmapUISDK;
//
//import com.skt.tmap.engine.navigation.route.RoutePlanType;
//
//
//
//
//
//import java.util.ArrayList;
//
//public class SubActivity extends AppCompatActivity {
//    private static final String TAG = "SubActivity";
//    private NavigationFragment navigationFragment;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sub);
//
//        Button buttonClose = findViewById(R.id.button_close);
//        buttonClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SubActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        navigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigationFragment);
//
//        if (navigationFragment != null) {
//            // 자동차 옵션 설정
//            CarOption carOption = new CarOption();
//            carOption.setCarType(TollCarType.Car);
//            carOption.setOilType(CarOilType.Gasoline);
//            carOption.setHipassOn(true);
//            navigationFragment.setCarOption(carOption);
//
//            // 현재 위치
//            Location currentLocation = SDKManager.getInstance().getCurrentPosition();
//            String currentName = VSMCoordinates.getAddressOffline(currentLocation.getLongitude(), currentLocation.getLatitude());
//            WayPoint startPoint = new WayPoint(currentName, new MapPoint(currentLocation.getLongitude(), currentLocation.getLatitude()));
//
//            // 경유지 설정 (없으면 null, 최대 5개)
//            ArrayList<WayPoint> passList = new ArrayList<>();
//            WayPoint pass1 = new WayPoint("안양역", new MapPoint(126.92292145335713, 37.4020688799069));
//            passList.add(pass1);
//
//            WayPoint pass2 = new WayPoint("판교역", new MapPoint(127.11292605972329, 37.39610065426602));
//            passList.add(pass2);
//
//            // 목적지
//            WayPoint endPoint = new WayPoint("강남역", new MapPoint(127.027813, 37.497999), "280181", (byte) 5);
//
//            // 경로 추천
//            ArrayList<RoutePlanType> planList = new ArrayList<>();
//            planList.add(RoutePlanType.Traffic_Recommend);
//            planList.add(RoutePlanType.Traffic_Free);
//
//            navigationFragment.requestRoute(startPoint, passList, endPoint, false, new TmapUISDK.RouteRequestListener() {
//                @Override
//                public void onSuccess() {
//                    Log.e(TAG, "requestRoute Success");
//                }
//
//                @Override
//                public void onFail(int i, @Nullable String s) {
//                    Log.e(TAG, "requestRoute Failed: " + i + " :: " + s);
//                }
//            }, planList);
//        }
//    }
//}



/////  안전운행까지 파일


//package com.example.pickle;
//
//import android.content.Intent;
//import android.location.Location;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.skt.tmap.engine.navigation.SDKManager;
//import com.skt.tmap.engine.navigation.network.ndds.CarOilType;
//import com.skt.tmap.engine.navigation.network.ndds.TollCarType;
//import com.skt.tmap.engine.navigation.route.RoutePlanType;
//import com.skt.tmap.engine.navigation.route.data.MapPoint;
//import com.skt.tmap.engine.navigation.route.data.WayPoint;
//import com.skt.tmap.vsm.coordinates.VSMCoordinates;
//import com.tmapmobility.tmap.tmapsdk.ui.data.CarOption;
//import com.tmapmobility.tmap.tmapsdk.ui.fragment.NavigationFragment;
//import com.tmapmobility.tmap.tmapsdk.ui.util.TmapUISDK;
//
//import java.util.ArrayList;
//
//public class SubActivity extends AppCompatActivity {
//    private static final String TAG = "SubActivity";
//    private NavigationFragment navigationFragment;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sub);
//
//        Button buttonClose = findViewById(R.id.button_close);
//        buttonClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SubActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        navigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigationFragment);
//
//        if (navigationFragment != null) {
//            // 자동차 옵션 설정
//            CarOption carOption = new CarOption();
//            carOption.setCarType(TollCarType.Car);
//            carOption.setOilType(CarOilType.Gasoline);
//            carOption.setHipassOn(true);
//            navigationFragment.setCarOption(carOption);
//
//            // 현재 위치
//            Location currentLocation = SDKManager.getInstance().getCurrentPosition();
//            String currentName = VSMCoordinates.getAddressOffline(currentLocation.getLongitude(), currentLocation.getLatitude());
//            WayPoint startPoint = new WayPoint(currentName, new MapPoint(currentLocation.getLongitude(), currentLocation.getLatitude()));
//
//            // 경유지 설정 (없으면 null, 최대 5개)
//            ArrayList<WayPoint> passList = new ArrayList<>();
//            WayPoint pass1 = new WayPoint("안양역", new MapPoint(126.92292145335713, 37.4020688799069));
//            passList.add(pass1);
//
//            WayPoint pass2 = new WayPoint("판교역", new MapPoint(127.11292605972329, 37.39610065426602));
//            passList.add(pass2);
//
//            // 목적지
//            WayPoint endPoint = new WayPoint("강남역", new MapPoint(127.027813, 37.497999), "280181", (byte) 5);
//
//            // 경로 추천
//            ArrayList<RoutePlanType> planList = new ArrayList<>();
//            planList.add(RoutePlanType.Traffic_Recommend);
//            planList.add(RoutePlanType.Traffic_Free);
//
//            navigationFragment.requestRoute(startPoint, passList, endPoint, false, new TmapUISDK.RouteRequestListener() {
//                @Override
//                public void onSuccess() {
//                    Log.e(TAG, "requestRoute Success");
//
//                    // 경로 요청 성공 시 안전 운행 시작
//                    navigationFragment.startSafeDrive();
//                }
//
//                @Override
//                public void onFail(int i, @Nullable String s) {
//                    Log.e(TAG, "requestRoute Failed: " + i + " :: " + s);
//                }
//            }, planList);
//        }
//    }
//}




//package com.example.pickle;
//
//import android.content.Intent;
//import android.location.Location;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.skt.tmap.engine.navigation.SDKManager;
//import com.skt.tmap.engine.navigation.network.ndds.CarOilType;
//import com.skt.tmap.engine.navigation.network.ndds.TollCarType;
//import com.skt.tmap.engine.navigation.route.RoutePlanType;
//import com.skt.tmap.engine.navigation.route.data.MapPoint;
//import com.skt.tmap.engine.navigation.route.data.WayPoint;
//import com.skt.tmap.vsm.coordinates.VSMCoordinates;
//import com.tmapmobility.tmap.tmapsdk.ui.data.CarOption;
//import com.tmapmobility.tmap.tmapsdk.ui.fragment.NavigationFragment;
//import com.tmapmobility.tmap.tmapsdk.ui.util.TmapUISDK;
//
//import java.util.ArrayList;
//
//public class SubActivity extends AppCompatActivity {
//    private static final String TAG = "SubActivity";
//    private NavigationFragment navigationFragment;
//    private SDKManager sdkManager;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sub);
//
//        Button buttonClose = findViewById(R.id.button_close);
//        buttonClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SubActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        Button buttonCurrentLocation = findViewById(R.id.button_current_location);
//        buttonCurrentLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                moveToCurrentLocation();
//            }
//        });
//
//        navigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigationFragment);
//
//        if (navigationFragment != null) {
//            // 자동차 옵션 설정
//            CarOption carOption = new CarOption();
//            carOption.setCarType(TollCarType.Car);
//            carOption.setOilType(CarOilType.Gasoline);
//            carOption.setHipassOn(true);
//            navigationFragment.setCarOption(carOption);
//
//            // 현재 위치
//            Location currentLocation = SDKManager.getInstance().getCurrentPosition();
//            String currentName = VSMCoordinates.getAddressOffline(currentLocation.getLongitude(), currentLocation.getLatitude());
//            WayPoint startPoint = new WayPoint(currentName, new MapPoint(currentLocation.getLongitude(), currentLocation.getLatitude()));
//
//            // 경유지 설정 (없으면 null, 최대 5개)
//            ArrayList<WayPoint> passList = new ArrayList<>();
//            WayPoint pass1 = new WayPoint("안양역", new MapPoint(126.92292145335713, 37.4020688799069));
//            passList.add(pass1);
//
//            WayPoint pass2 = new WayPoint("판교역", new MapPoint(127.11292605972329, 37.39610065426602));
//            passList.add(pass2);
//
//            // 목적지
//            WayPoint endPoint = new WayPoint("강남역", new MapPoint(127.027813, 37.497999), "280181", (byte) 5);
//
//            // 경로 추천
//            ArrayList<RoutePlanType> planList = new ArrayList<>();
//            planList.add(RoutePlanType.Traffic_Recommend);
//            planList.add(RoutePlanType.Traffic_Free);
//
//            navigationFragment.requestRoute(startPoint, passList, endPoint, false, new TmapUISDK.RouteRequestListener() {
//                @Override
//                public void onSuccess() {
//                    Log.e(TAG, "requestRoute Success");
//
//                    // 경로 요청 성공 시 안전 운행 시작
//                    navigationFragment.startSafeDrive();
//                }
//
//                @Override
//                public void onFail(int i, @Nullable String s) {
//                    Log.e(TAG, "requestRoute Failed: " + i + " :: " + s);
//                }
//            }, planList);
//        }
//    }
//
//    private void moveToCurrentLocation() {
//        Location currentLocation = SDKManager.getInstance().getCurrentPosition();
//        if (currentLocation != null) {
//            // 예: SDKManager를 통해 지도의 중심을 현재 위치로 이동
//            // 이 부분은 SDKManager 클래스의 기능을 참조하여 구현
//            Log.i(TAG, "Current location: " + currentLocation.getLatitude() + ", " + currentLocation.getLongitude());
//        } else {
//            Log.e(TAG, "Current location is null");
//        }
//    }
//}




///마커추가 까지 파일

//package com.example.pickle;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.location.Location;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.skt.tmap.engine.navigation.SDKManager;
//import com.skt.tmap.engine.navigation.network.ndds.CarOilType;
//import com.skt.tmap.engine.navigation.network.ndds.TollCarType;
//import com.skt.tmap.engine.navigation.route.RoutePlanType;
//import com.skt.tmap.engine.navigation.route.data.MapPoint;
//import com.skt.tmap.engine.navigation.route.data.WayPoint;
//import com.skt.tmap.vsm.coordinates.VSMCoordinates;
//import com.skt.tmap.vsm.data.VSMMapPoint;
//import com.skt.tmap.vsm.map.marker.MarkerImage;
//import com.skt.tmap.vsm.map.marker.VSMMarkerManager;
//import com.skt.tmap.vsm.map.marker.VSMMarkerPoint;
//import com.tmapmobility.tmap.tmapsdk.ui.data.CarOption;
//import com.tmapmobility.tmap.tmapsdk.ui.fragment.NavigationFragment;
//import com.tmapmobility.tmap.tmapsdk.ui.util.TmapUISDK;
//import com.tmapmobility.tmap.tmapsdk.ui.view.MapConstant;
//
//import java.util.ArrayList;
//
//public class SubActivity extends AppCompatActivity {
//    private static final String TAG = "SubActivity";
//    private NavigationFragment navigationFragment;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sub); // Ensure this matches your layout file name
//
//        // Ensure button IDs match those defined in activity_sub.xml
//        Button buttonClose = findViewById(R.id.button_close);
//        Button buttonCurrentLocation = findViewById(R.id.button_current_location);
//
//        buttonClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SubActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        buttonCurrentLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                moveToCurrentLocation();
//                addMarker();
//            }
//        });
//
//        // Ensure the fragment ID matches that defined in activity_sub.xml
//        navigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigationFragment);
//
//        if (navigationFragment != null) {
//            // 자동차 옵션 설정
//            CarOption carOption = new CarOption();
//            carOption.setCarType(TollCarType.Car);
//            carOption.setOilType(CarOilType.Gasoline);
//            carOption.setHipassOn(true);
//            navigationFragment.setCarOption(carOption);
//
//            // 현재 위치
//            Location currentLocation = SDKManager.getInstance().getCurrentPosition();
//            String currentName = VSMCoordinates.getAddressOffline(currentLocation.getLongitude(), currentLocation.getLatitude());
//            WayPoint startPoint = new WayPoint(currentName, new MapPoint(currentLocation.getLongitude(), currentLocation.getLatitude()));
//
//            // 경유지 설정 (없으면 null, 최대 5개)
//            ArrayList<WayPoint> passList = new ArrayList<>();
//            WayPoint pass1 = new WayPoint("안양역", new MapPoint(126.92292145335713, 37.4020688799069));
//            passList.add(pass1);
//
//            WayPoint pass2 = new WayPoint("판교역", new MapPoint(127.11292605972329, 37.39610065426602));
//            passList.add(pass2);
//
//            // 목적지
//            WayPoint endPoint = new WayPoint("강남역", new MapPoint(127.027813, 37.497999), "280181", (byte) 5);
//
//            // 경로 추천
//            ArrayList<RoutePlanType> planList = new ArrayList<>();
//            planList.add(RoutePlanType.Traffic_Recommend);
//            planList.add(RoutePlanType.Traffic_Free);
//
//            navigationFragment.requestRoute(startPoint, passList, endPoint, false, new TmapUISDK.RouteRequestListener() {
//                @Override
//                public void onSuccess() {
//                    Log.e(TAG, "requestRoute Success");
//
//                    // 경로 요청 성공 시 안전 운행 시작
//                    navigationFragment.startSafeDrive();
//                }
//
//                @Override
//                public void onFail(int i, @Nullable String s) {
//                    Log.e(TAG, "requestRoute Failed: " + i + " :: " + s);
//                }
//            }, planList);
//        }
//    }
//
//    private void moveToCurrentLocation() {
//        Location currentLocation = SDKManager.getInstance().getCurrentPosition();
//        if (currentLocation != null) {
//            // 예: SDKManager를 통해 지도의 중심을 현재 위치로 이동
//            // 이 부분은 SDKManager 클래스의 기능을 참조하여 구현
//            Log.i(TAG, "Current location: " + currentLocation.getLatitude() + ", " + currentLocation.getLongitude());
//        } else {
//            Log.e(TAG, "Current location is null");
//        }
//    }
//
//    private void addMarker() {
//        if (navigationFragment == null) {
//            Log.e(TAG, "navigationFragment is null");
//            return;
//        }
//
//        String markerID = "TEST";
//        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.poi); // Ensure you have a drawable named 'poi'
//        VSMMarkerPoint marker = new VSMMarkerPoint(markerID);
//
//        marker.setIcon(MarkerImage.fromBitmap(icon));
//        marker.setShowPriority(MapConstant.MarkerRenderingPriority.DEFAULT_PRIORITY);
//        marker.setText("TEST");
//
//        // 현재 위치 보다 조금 옆에 마커를 찍는다.
//        VSMMapPoint position = new VSMMapPoint(SDKManager.getInstance().getCurrentPosition().getLongitude() - 0.0002, SDKManager.getInstance().getCurrentPosition().getLatitude() + 0.0002);
//        marker.setPosition(position);
//
//        VSMMarkerManager markerManager = navigationFragment.getMapView().getMarkerManager();
//        if (markerManager == null) {
//            Log.e(TAG, "마커 매니저 NULL");
//            return;
//        }
//
//        markerManager.removeMarker(markerID);
//        markerManager.addMarker(marker);
//    }
//}



package com.example.pickle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.skt.tmap.engine.navigation.SDKManager;
import com.skt.tmap.engine.navigation.network.ndds.CarOilType;
import com.skt.tmap.engine.navigation.network.ndds.TollCarType;
import com.skt.tmap.engine.navigation.route.RoutePlanType;
import com.skt.tmap.engine.navigation.route.data.MapPoint;
import com.skt.tmap.engine.navigation.route.data.WayPoint;
import com.skt.tmap.vsm.coordinates.VSMCoordinates;
import com.skt.tmap.vsm.data.VSMMapPoint;
import com.skt.tmap.vsm.map.marker.MarkerImage;
import com.skt.tmap.vsm.map.marker.VSMMarkerManager;
import com.skt.tmap.vsm.map.marker.VSMMarkerPoint;
import com.tmapmobility.tmap.tmapsdk.ui.data.CarOption;
import com.tmapmobility.tmap.tmapsdk.ui.fragment.NavigationFragment;
import com.tmapmobility.tmap.tmapsdk.ui.util.TmapUISDK;
import com.tmapmobility.tmap.tmapsdk.ui.view.MapConstant;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSIONS = 1001;
    private static final String TAG = "SubActivity";
    private NavigationFragment navigationFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Button buttonClose = findViewById(R.id.button_close);
        Button buttonCurrentLocation = findViewById(R.id.button_current_location);
        Button buttonAddMarker = findViewById(R.id.button_add_marker);

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        buttonCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToCurrentLocation();
            }
        });

        buttonAddMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMarker();
            }
        });

        navigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigationFragment);

        if (navigationFragment != null) {
            // 자동차 옵션 설정
            CarOption carOption = new CarOption();
            carOption.setCarType(TollCarType.Car);
            carOption.setOilType(CarOilType.Gasoline);
            carOption.setHipassOn(true);
            navigationFragment.setCarOption(carOption);

            // 현재 위치
            Location currentLocation = SDKManager.getInstance().getCurrentPosition();
            String currentName = VSMCoordinates.getAddressOffline(currentLocation.getLongitude(), currentLocation.getLatitude());
            WayPoint startPoint = new WayPoint(currentName, new MapPoint(currentLocation.getLongitude(), currentLocation.getLatitude()));

            // 경유지 설정 (없으면 null, 최대 5개)
            ArrayList<WayPoint> passList = new ArrayList<>();
            WayPoint pass1 = new WayPoint("안양역", new MapPoint(126.92292145335713, 37.4020688799069));
            passList.add(pass1);

            WayPoint pass2 = new WayPoint("판교역", new MapPoint(127.11292605972329, 37.39610065426602));
            passList.add(pass2);

            // 목적지
            WayPoint endPoint = new WayPoint("강남역", new MapPoint(127.027813, 37.497999), "280181", (byte) 5);

            // 경로 추천
            ArrayList<RoutePlanType> planList = new ArrayList<>();
            planList.add(RoutePlanType.Traffic_Recommend);
            planList.add(RoutePlanType.Traffic_Free);

            navigationFragment.requestRoute(startPoint, passList, endPoint, false, new TmapUISDK.RouteRequestListener() {
                @Override
                public void onSuccess() {
                    Log.e(TAG, "requestRoute Success");

                    // 경로 요청 성공 시 안전 운행 시작
                    navigationFragment.startSafeDrive();
                }

                @Override
                public void onFail(int i, @Nullable String s) {
                    Log.e(TAG, "requestRoute Failed: " + i + " :: " + s);
                }
            }, planList);
        }
    }

    private void moveToCurrentLocation() {
        Location currentLocation = SDKManager.getInstance().getCurrentPosition();
        if (currentLocation != null) {
            // 예: SDKManager를 통해 지도의 중심을 현재 위치로 이동
            // 이 부분은 SDKManager 클래스의 기능을 참조하여 구현
            Log.i(TAG, "Current location: " + currentLocation.getLatitude() + ", " + currentLocation.getLongitude());
        } else {
            Log.e(TAG, "Current location is null");
        }
    }

    private void addMarker() {
        if (navigationFragment == null) {
            Log.e(TAG, "navigationFragment is null");
            return;
        }

        String markerID = "TEST";
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.poi); // Ensure you have a drawable named 'poi'
        VSMMarkerPoint marker = new VSMMarkerPoint(markerID);

        marker.setIcon(MarkerImage.fromBitmap(icon));
        marker.setShowPriority(MapConstant.MarkerRenderingPriority.DEFAULT_PRIORITY);
        marker.setText("TEST");

        // 현재 위치 보다 조금 옆에 마커를 찍는다.
        Location currentLocation = SDKManager.getInstance().getCurrentPosition();
        if (currentLocation != null) {
            VSMMapPoint position = new VSMMapPoint(currentLocation.getLongitude() - 0.0002, currentLocation.getLatitude() + 0.0002);
            marker.setPosition(position);

            VSMMarkerManager markerManager = navigationFragment.getMapView().getMarkerManager();
            if (markerManager == null) {
                Log.e(TAG, "마커 매니저 NULL");
                return;
            }

            markerManager.removeMarker(markerID);
            markerManager.addMarker(marker);
        } else {
            Log.e(TAG, "Current location is null");
        }
    }
}
