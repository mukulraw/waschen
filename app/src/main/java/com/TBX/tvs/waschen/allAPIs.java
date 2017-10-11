package com.TBX.tvs.waschen;

import com.TBX.tvs.waschen.AddBucketPOJO.AddBean;
import com.TBX.tvs.waschen.CheckoutPOJO.CheckBean;
import com.TBX.tvs.waschen.ClearPOJO.ClearBean;
import com.TBX.tvs.waschen.ContactusPOJO.ContactBean;
import com.TBX.tvs.waschen.CreatePOJO.CreateBean;
import com.TBX.tvs.waschen.FaqPOJO.FaqBean;
import com.TBX.tvs.waschen.GetBucketPOJO.GetBean;
import com.TBX.tvs.waschen.LoginPOJO.LoginBean;
import com.TBX.tvs.waschen.OrderDetailPOJO.DetailBean;
import com.TBX.tvs.waschen.OrderPojo.OrderBean;
import com.TBX.tvs.waschen.RemovePOJO.RemoveBean;
import com.TBX.tvs.waschen.SProductPOJO.ProductBean;
import com.TBX.tvs.waschen.ServicesPOJO.ServiceBean;
import com.TBX.tvs.waschen.SocialPOJO.SocialBean;
import com.TBX.tvs.waschen.SubmitPOJO.SubmitBean;
import com.TBX.tvs.waschen.UpdatePOJO.UpdateBean;
import com.TBX.tvs.waschen.UploadImagePOJO.UploadBean;
import com.TBX.tvs.waschen.ViewProfilePOJO.ViewBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface allAPIs {

    @Multipart
    @POST("waschen_api/sign_up.php")
    Call<CreateBean> create (
            @Part("name") String n,
            @Part("email") String m ,
            @Part("password") String c ,
            @Part("phone") String ph

    );

    @GET("waschen_api/services_list.php")
    Call<ServiceBean> service (

    );

    @Multipart
    @POST("waschen_api/login.php")
    Call<LoginBean> login (
            @Part("email") String n,
            @Part("password") String c
    );

    @Multipart
    @POST("waschen_api/product_list.php")
    Call<ProductBean> product (
            @Part("categoryId") String c

    );

    @Multipart
    @POST("waschen_api/get_bucket.php")
    Call<GetBean> get (
            @Part("userId") String id

    );


    @Multipart
    @POST("waschen_api/add_bucket.php")
    Call<AddBean> add (
            @Part("userId") String id ,
            @Part("productId") String pro ,
            @Part("quantity") String quan

    );

    @Multipart
    @POST("waschen_api/product_list.php")
    Call<UpdateBean> update (
            @Part("userId") String id ,
            @Part("productId") String pro ,
            @Part("quantity") String quan

    );

    @Multipart
    @POST("waschen_api/remove_bucket.php")
    Call<RemoveBean> remove (
            @Part("userId") String id ,
            @Part("productId") String pro

    );

    @Multipart
    @POST("/waschen_api/clear_bucket.php")
    Call<UpdateBean> clear (
            @Part("userId") String id 

    );

    @Multipart
    @POST("waschen_api/view_profile.php")
    Call<ViewBean> view (
            @Part("userId") String id

    );
    @Multipart
    @POST("waschen_api/product_list.php")
    Call<UploadBean> upload (
            @Part("userId") String id ,
            @Part("image") String image

    );

    @Multipart
    @POST("waschen_api/product_list.php")
    Call<CheckBean> check (
            @Part("userId") String id ,
            @Part("amount") String image ,
            @Part("quantity") String quan

    );

    @GET("waschen_api/faq.php")
    Call<FaqBean> faq (
    );

    @Multipart
    @POST("waschen_api/social_sign_up.php")
    Call<SocialBean> social (
            @Part("name") String name ,
            @Part("email") String email ,
            @Part("pid") String pid ,
            @Part("image") String image

    );


    @Multipart
    @POST("waschen_api/product_list.php")
    Call<ProductBean> product (
            @Part("name") String name ,
            @Part("email") String email ,
            @Part("pid") String pid ,
            @Part("image") String image

    );

    @Multipart
    @POST("waschen_api/clear_bucket.php")
    Call<ClearBean> clear (

    );

    @Multipart
    @POST("waschen_api/order_history.php")
    Call<OrderBean> order (
            @Part("userId") String id
    );


    @Multipart
    @POST("waschen_api/order_history.php")
    Call<DetailBean> detail (
            @Part("userId") String id ,
            @Part("orderId") String order

    );


    @GET("waschen_api/owner_address.php")
    Call<ContactBean> contact (

    );

    @Multipart
    @POST("waschen_api/contact_us.php")
    Call<SubmitBean> submit (
            @Part("name") String id ,
            @Part("email") String e ,
            @Part("phone") String p ,
            @Part("comment") String c

    );


}
