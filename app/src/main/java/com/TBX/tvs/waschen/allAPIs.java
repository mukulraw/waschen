package com.TBX.tvs.waschen;

import com.TBX.tvs.waschen.AddBucketPOJO.AddBean;
import com.TBX.tvs.waschen.BucketCountPOJO.BucketCountBean;
import com.TBX.tvs.waschen.CancelPOJO.CancelBean;
import com.TBX.tvs.waschen.CashPOJO.CashBean;
import com.TBX.tvs.waschen.CheckoutPOJO.CheckBean;
import com.TBX.tvs.waschen.ClearPOJO.ClearBean;
import com.TBX.tvs.waschen.ContactusPOJO.ContactBean;
import com.TBX.tvs.waschen.CouponPOJO.CouponBean;
import com.TBX.tvs.waschen.CreatePOJO.CreateBean;
import com.TBX.tvs.waschen.FaqPOJO.FaqBean;
import com.TBX.tvs.waschen.GetBucketPOJO.GetBean;
import com.TBX.tvs.waschen.GetZipPOJO.GetZipBean;
import com.TBX.tvs.waschen.GrandTotalPOJO.GrandBean;
import com.TBX.tvs.waschen.LoginPOJO.LoginBean;
import com.TBX.tvs.waschen.NudgePOJO.NudgeBean;
import com.TBX.tvs.waschen.OrderDetailPOJO.DetailBean;
import com.TBX.tvs.waschen.OrderPojo.OrderBean;
import com.TBX.tvs.waschen.RemovePOJO.RemoveBean;
import com.TBX.tvs.waschen.SProductPOJO.ProductBean;
import com.TBX.tvs.waschen.ServicesPOJO.ServiceBean;
import com.TBX.tvs.waschen.SocialPOJO.SocialBean;
import com.TBX.tvs.waschen.SubmitPOJO.SubmitBean;
import com.TBX.tvs.waschen.UpdatePOJO.UpdateBean;
import com.TBX.tvs.waschen.UpdateProfilePOJO.UpdateProfileBean;
import com.TBX.tvs.waschen.UploadImagePOJO.UploadBean;
import com.TBX.tvs.waschen.ViewMorePOJO.ViewMoreBean;
import com.TBX.tvs.waschen.ViewProfilePOJO.ViewBean;

import okhttp3.MultipartBody;
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

    @GET("waschen_api/getZip.php")
    Call<GetZipBean> bean (

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
            @Part("userid") String id,
            @Part("cartid") String cat

    );


    /*@Multipart
    @POST("waschen_api/get_bucket.php")
    Call<GetBean> updateBusket(
            @Part("userid") String id,
            @Part("cartid") String cat

    );*/


    @Multipart
    @POST("waschen_api/add_bucket.php")
    Call<AddBean> add (
            @Part("userId") String id ,
            @Part("productId") String pro ,
            @Part("quantity") String quan ,
            @Part("price") String price

    );

    @Multipart
    @POST("waschen_api/product_list.php")
    Call<AddBean> update (
            @Part("userId") String id ,
            @Part("productId") String pro ,
            @Part("quantity") String quan ,
            @Part("price") String price

    );

    @Multipart
    @POST("waschen_api/remove_bucket.php")
    Call<GetBean> remove (
            @Part("userId") String id ,
            @Part("cartid") String cart ,
            @Part("productId") String pro

    );

    @Multipart
    @POST("/waschen_api/clear_bucket.php")
    Call<GetBean> clear (
            @Part("userId") String id ,
            @Part("cartid") String cart

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
            @Part MultipartBody.Part file

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
    Call<CreateBean> social (
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

    @GET("waschen_api/bucket_count.php")
    Call<BucketCountBean> getBucketCount ();



    @Multipart
    @POST("waschen_api/update_profile.php")
    Call<UpdateProfileBean> updateprofile (
            @Part("userId") String id ,
            @Part("name") String n ,
            @Part("phone") String p ,
            @Part("address") String a ,
            @Part("city") String c ,
            @Part("zip") String z ,
            @Part("country") String country,
            @Part("birthday") String b,
            @Part("landmark") String l,
            @Part("state") String state
    );


    @Multipart
    @POST("waschen_api/coupon_value.php")
    Call<CouponBean> coupon (
            @Part("coupon_code") String id ,
            @Part("userid") String e ,
            @Part("cartid") String p
    );


    @Multipart
    @POST("waschen_api/grandtotal_list.php")
    Call<GrandBean> grand (
            @Part("coupon_price") String id ,
            @Part("userid") String u ,
            @Part("cartid") String c ,
            @Part("delivery_price") String d
    );



    @Multipart
    @POST("waschen_api/cashondelivery.php")
    Call<CashBean> cash (
            @Part("userid") String id ,
            @Part("cartid") String n ,
            @Part("billname") String p ,
            @Part("billemail") String a ,
            @Part("billaddress") String c ,
            @Part("billcity") String city ,
            @Part("billstate") String s,
            @Part("billzip") String z,
            @Part("billphone") String phone,
            @Part("shipfname") String fn,
            @Part("shiplname") String ln,
            @Part("shipphone") String sp,
            @Part("shipaddress") String sa,
            @Part("shipcity") String sci,
            @Part("shipstate") String ss,
            @Part("shipzip") String sz,
            @Part("shipcountry") String sc,
            @Part("dilivery_method") String d,
            @Part("dilivery_price") String dp,
            @Part("coupon_price") String cp,
            @Part("cart_price") String cpr,
            @Part("gst_tax") String gta,
            @Part("grand_total") String gt,
            @Part("date") String date

    );



    @Multipart
    @POST("waschen_api/add_nudge.php")
    Call<NudgeBean> nudge (
            @Part("email") String c,
            @Part("username") String u

    );



    @Multipart
    @POST("waschen_api/orderbyid.php")
    Call<ViewMoreBean> more (
            @Part("userId") String c,
            @Part("orderId") String u

    );



    @Multipart
    @POST("waschen_api/cancel_order.php")
    Call<CancelBean> cancel (
            @Part("userId") String c,
            @Part("orderId") String u

    );



}
