package com.example.today.apiclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TaskInterface {
    @GET("restful/")
    Call<List<Task>> getTasks();

    @FormUrlEncoded
    @POST("restful/")
    Call<Task> postTask(@Field("title")String title, @Field("desc")String desc);

    @DELETE("restful/")
    Call<Task> deleteTask(@Query("id")int id);

    @FormUrlEncoded
    @POST("restful/")
    Call<Task> updateTask(@Field("title")String title, @Field("desc")String desc, @Field("id")int id);

    @GET("restful/array.php")
    Call<List<Task>> getSubTasks();

    @FormUrlEncoded
    @POST("restful/array.php")
    Call<Task> postSubTask(@Field("subtask")String subtask, @Field("key")int key);

    @DELETE("restful/array.php")
    Call<Task> deleteSubTask(@Query("id")int id);
}
