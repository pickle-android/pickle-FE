////geocoding완, 경로설정하면 그쪽으로 내비 안내까지 완

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
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class SubActivity extends AppCompatActivity {
//    private static final int REQUEST_CODE_PERMISSIONS = 1001;
//    private static final String TAG = "SubActivity";
//    private NavigationFragment navigationFragment;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sub);
//
//        Button buttonClose = findViewById(R.id.button_close);
//        Button buttonCurrentLocation = findViewById(R.id.button_current_location);
//        Button buttonAddMarker = findViewById(R.id.button_add_marker);
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
//            }
//        });
//
//        buttonAddMarker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addMarker();
//            }
//        });
//
//        navigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigationFragment);
//
//        if (navigationFragment != null) {
//            // Intent로부터 데이터 가져오기
//            Intent intent = getIntent();
//            String selectedPlace = intent.getStringExtra("selectedPlace");
//            double latitude = intent.getDoubleExtra("latitude", 0.0);
//            double longitude = intent.getDoubleExtra("longitude", 0.0);
//
//            // 주소를 한글 우선으로 변환
//            String formattedAddress = formatAddress(selectedPlace);
//
//            // Logcat에 출력
//            Log.d(TAG, "Selected Place: " + selectedPlace);
//
//            //formattedAddress은 내비 경로안내 주소를 한글먼저보여주고 뒤이어 영어 주소알려줌
//            Log.d(TAG, "Formatted Address: " + formattedAddress);
//            Log.d(TAG, "Latitude: " + latitude);
//            Log.d(TAG, "Longitude: " + longitude);
//
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
//            // 경유지를 추가하고 싶다면 여기에 추가
//
//            // 목적지
//            WayPoint endPoint = new WayPoint(formattedAddress, new MapPoint(longitude, latitude), null, (byte) 5);
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
//
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
//        Location currentLocation = SDKManager.getInstance().getCurrentPosition();
//        if (currentLocation != null) {
//            VSMMapPoint position = new VSMMapPoint(currentLocation.getLongitude(), currentLocation.getLatitude());
//            marker.setPosition(position);
//
//            VSMMarkerManager markerManager = navigationFragment.getMapView().getMarkerManager();
//            if (markerManager == null) {
//                Log.e(TAG, "마커 매니저 NULL");
//                return;
//            }
//
//            markerManager.removeMarker(markerID);
//            markerManager.addMarker(marker);
//        } else {
//            Log.e(TAG, "Current location is null");
//        }
//    }
//
//    // 주소를 한글 우선으로 변환하는 메서드 추가
//    private String formatAddress(String address) {
//        StringBuilder koreanPart = new StringBuilder();
//        StringBuilder englishPart = new StringBuilder();
//
//        // 정규 표현식을 사용하여 한글과 영어 부분을 분리
//        Pattern pattern = Pattern.compile("[가-힣]+|[a-zA-Z\\s,]+");
//        Matcher matcher = pattern.matcher(address);
//        while (matcher.find()) {
//            String part = matcher.group();
//            if (part.matches("[가-힣]+")) {
//                koreanPart.append(part).append(" ");
//            } else {
//                englishPart.append(part).append(" ");
//            }
//        }
//
//        return koreanPart.toString().trim() + " " + englishPart.toString().trim();
//    }
//}


package com.example.pickle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.api.ApiException;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import com.skt.tmap.engine.navigation.SDKManager;
import com.skt.tmap.engine.navigation.network.ndds.CarOilType;
import com.skt.tmap.engine.navigation.network.ndds.TollCarType;
import com.skt.tmap.engine.navigation.route.RoutePlanType;
import com.skt.tmap.engine.navigation.route.data.MapPoint;
import com.skt.tmap.engine.navigation.route.data.WayPoint;
import com.skt.tmap.vsm.coordinates.VSMCoordinates;
import com.skt.tmap.vsm.data.VSMMapPoint;
//import com.skt.tmap.vsm.map.MapView;
import com.skt.tmap.vsm.map.marker.MarkerImage;
import com.skt.tmap.vsm.map.marker.VSMMarkerManager;
import com.skt.tmap.vsm.map.marker.VSMMarkerPoint;
import com.tmapmobility.tmap.tmapsdk.ui.data.CarOption;
import com.tmapmobility.tmap.tmapsdk.ui.fragment.NavigationFragment;
import com.tmapmobility.tmap.tmapsdk.ui.util.TmapUISDK;
import com.tmapmobility.tmap.tmapsdk.ui.view.MapConstant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SubActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSIONS = 1001;
    private static final String TAG = "SubActivity";
    private NavigationFragment navigationFragment;
    private PlacesClient placesClient;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // Initialize Places
        Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
        placesClient = Places.createClient(this);

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

        // 위치 권한 요청
        getLocationPermission();

        navigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigationFragment);

        if (navigationFragment != null) {
            // Intent로부터 데이터 가져오기
            Intent intent = getIntent();
            String selectedPlace = intent.getStringExtra("selectedPlace");
            double latitude = intent.getDoubleExtra("latitude", 0.0);
            double longitude = intent.getDoubleExtra("longitude", 0.0);

            // 주소를 한글 우선으로 변환
            String formattedAddress = formatAddress(selectedPlace);

            // Logcat에 출력
            Log.d(TAG, "Selected Place: " + selectedPlace);
            Log.d(TAG, "Formatted Address: " + formattedAddress);
            Log.d(TAG, "Latitude: " + latitude);
            Log.d(TAG, "Longitude: " + longitude);

            // 주변 가게 정보 검색
            searchNearbyPlaces(latitude, longitude);

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
            // 경유지를 추가하고 싶다면 여기에 추가

            // 목적지
            WayPoint endPoint = new WayPoint(formattedAddress, new MapPoint(longitude, latitude), null, (byte) 5);

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
            VSMMapPoint position = new VSMMapPoint(currentLocation.getLongitude(), currentLocation.getLatitude());
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

    // 주소를 한글 우선으로 변환하는 메서드 추가
    private String formatAddress(String address) {
        StringBuilder koreanPart = new StringBuilder();
        StringBuilder englishPart = new StringBuilder();

        // 정규 표현식을 사용하여 한글과 영어 부분을 분리
        Pattern pattern = Pattern.compile("[가-힣]+|[a-zA-Z\\s,]+");
        Matcher matcher = pattern.matcher(address);
        while (matcher.find()) {
            String part = matcher.group();
            if (part.matches("[가-힣]+")) {
                koreanPart.append(part).append(" ");
            } else {
                englishPart.append(part).append(" ");
            }
        }

        return koreanPart.toString().trim() + " " + englishPart.toString().trim();
    }

    // 위치 권한 요청 메서드 추가
    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        locationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true;
                }
            }
        }
    }

    // 주변 가게 정보를 검색하는 메서드 수정
    private void searchNearbyPlaces(double latitude, double longitude) {
        if (locationPermissionGranted) {
            OkHttpClient client = new OkHttpClient();
            String apiKey = getString(R.string.google_maps_key);
            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
                    + latitude + "," + longitude + "&radius=1500&type=store&key=" + apiKey;

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "Nearby places search failed: " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        Log.e(TAG, "Nearby places search response failed: " + response.message());
                        return;
                    }

                    String responseData = response.body().string();
                    Log.d(TAG, "Nearby places response: " + responseData);

                    // 여기서 JSON 응답을 파싱하고 가게 정보를 추출할 수 있습니다.
                }
            });
        } else {
            Log.e(TAG, "Location permission not granted");
        }
    }
}





