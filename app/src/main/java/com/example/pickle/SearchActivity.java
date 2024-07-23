////geocoding 완료
//
//package com.example.pickle;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
//import android.widget.ListView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.common.api.Status;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.libraries.places.api.Places;
//import com.google.android.libraries.places.api.model.AutocompletePrediction;
//import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
//import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
//import com.google.android.libraries.places.api.net.PlacesClient;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class SearchActivity extends AppCompatActivity {
//
//    private static final String TAG = "SearchActivity";
//    private AutoCompleteTextView searchTextView;
//    private ListView listView;
//    private PlacesClient placesClient;
//    private List<String> suggestionList;
//    private ArrayAdapter<String> adapter;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
//
//        searchTextView = findViewById(R.id.search_text_view);
//        listView = findViewById(R.id.suggestion_list_view);
//
//        // Initialize Places
//        if (!Places.isInitialized()) {
//            Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
//        }
//        placesClient = Places.createClient(this);
//
//        suggestionList = new ArrayList<>();
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, suggestionList);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedPlace = adapter.getItem(position);
//                searchTextView.setText(selectedPlace); // 선택된 주소를 AutoCompleteTextView에 설정
//                Log.d(TAG, "Selected Place: " + selectedPlace); // Logcat에 선택된 주소 출력
//                getLatLngFromAddress(selectedPlace); // 주소를 위도와 경도로 변환하여 Logcat에 출력
//            }
//        });
//
//        searchTextView.addTextChangedListener(new android.text.TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                getAutocompletePredictions(charSequence.toString());
//            }
//
//            @Override
//            public void afterTextChanged(android.text.Editable editable) {}
//        });
//    }
//
//    private void getAutocompletePredictions(String query) {
//        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
//        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
//                .setSessionToken(token)
//                .setQuery(query)
//                .setCountries("KR")
//                .build();
//
//        placesClient.findAutocompletePredictions(request).addOnSuccessListener(response -> {
//            suggestionList.clear();
//            for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
//                suggestionList.add(prediction.getFullText(null).toString());
//            }
//            adapter.notifyDataSetChanged();
//        }).addOnFailureListener(exception -> {
//            Log.e(TAG, "Place not found: " + exception.getMessage());
//        });
//    }
//
//    private void getLatLngFromAddress(String address) {
//        OkHttpClient client = new OkHttpClient();
//        String apiKey = getString(R.string.google_maps_key);
//        String encodedAddress = "";
//        try {
//            encodedAddress = URLEncoder.encode(address, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + encodedAddress + "&key=" + apiKey;
//
//        Log.d(TAG, "Geocoding API URL: " + url); // 요청 URL을 로그에 출력
//
//        Request request = new Request.Builder().url(url).build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e(TAG, "Geocoding API call failed: " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    String responseData = response.body().string();
////                    Log.d(TAG, "Geocoding API Response: " + responseData); // 응답 데이터를 로그에 출력
//                    try {
//                        JSONObject jsonObject = new JSONObject(responseData);
//                        JSONArray results = jsonObject.getJSONArray("results");
//                        if (results.length() > 0) {
//                            JSONObject location = results.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
//                            double lat = location.getDouble("lat");
//                            double lng = location.getDouble("lng");
//                            Log.d(TAG, "Latitude: " + lat + ", Longitude: " + lng);
//                        } else {
//                            Log.e(TAG, "No results found for the given address.");
//                        }
//                    } catch (Exception e) {
//                        Log.e(TAG, "Failed to parse JSON response: " + e.getMessage());
//                    }
//                } else {
//                    Log.e(TAG, "Geocoding API call unsuccessful: " + response.message());
//                }
//            }
//        });
//    }
//}

package com.example.pickle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private AutoCompleteTextView searchTextView;
    private ListView listView;
    private PlacesClient placesClient;
    private List<String> suggestionList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchTextView = findViewById(R.id.search_text_view);
        listView = findViewById(R.id.suggestion_list_view);

        // Initialize Places
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
        }
        placesClient = Places.createClient(this);

        suggestionList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, suggestionList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedPlace = adapter.getItem(position);
                searchTextView.setText(selectedPlace); // 선택된 주소를 AutoCompleteTextView에 설정
                Log.d(TAG, "Selected Place: " + selectedPlace); // Logcat에 선택된 주소 출력
                getLatLngFromAddress(selectedPlace); // 주소를 위도와 경도로 변환하여 SubActivity로 전달
            }
        });

        searchTextView.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getAutocompletePredictions(charSequence.toString());
            }

            @Override
            public void afterTextChanged(android.text.Editable editable) {}
        });
    }

    private void getAutocompletePredictions(String query) {
        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                .setSessionToken(token)
                .setQuery(query)
                .setCountries("KR")
                .build();

        placesClient.findAutocompletePredictions(request).addOnSuccessListener(response -> {
            suggestionList.clear();
            for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                suggestionList.add(prediction.getFullText(null).toString());
            }
            adapter.notifyDataSetChanged();
        }).addOnFailureListener(exception -> {
            Log.e(TAG, "Place not found: " + exception.getMessage());
        });
    }

    private void getLatLngFromAddress(String address) {
        OkHttpClient client = new OkHttpClient();
        String apiKey = getString(R.string.google_maps_key);
        String encodedAddress = "";
        try {
            encodedAddress = URLEncoder.encode(address, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + encodedAddress + "&key=" + apiKey;

        Log.d(TAG, "Geocoding API URL: " + url); // 요청 URL을 로그에 출력

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Geocoding API call failed: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    Log.d(TAG, "Geocoding API Response: " + responseData); // 응답 데이터를 로그에 출력
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        JSONArray results = jsonObject.getJSONArray("results");
                        if (results.length() > 0) {
                            JSONObject location = results.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
                            double lat = location.getDouble("lat");
                            double lng = location.getDouble("lng");
                            Log.d(TAG, "Latitude: " + lat + ", Longitude: " + lng);

                            // SubActivity로 데이터 전달
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(SearchActivity.this, SubActivity.class);
                                    intent.putExtra("selectedPlace", address);
                                    intent.putExtra("latitude", lat);
                                    intent.putExtra("longitude", lng);

                                    // 1초 후에 SubActivity로 이동
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            startActivity(intent);
                                        }
                                    }, 1000);
                                }
                            });
                        } else {
                            Log.e(TAG, "No results found for the given address.");
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Failed to parse JSON response: " + e.getMessage());
                    }
                } else {
                    Log.e(TAG, "Geocoding API call unsuccessful: " + response.message());
                }
            }
        });
    }
}
