package api;

import api.models.flashpost.FlashpostRequest;
import io.restassured.response.Response;

public class FlashpostsService{

    private final RequestManager requestManager;
    private static final String flashPostsEndpoint = "/api/flashposts";

    public FlashpostsService(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public Response getFlashposts(){
        return requestManager.get(flashPostsEndpoint);
    }

    public Response getFlashpostsById(int id){
        return requestManager.get(flashPostsEndpoint+"/"+id);
    }

    public Response createFlashpost(FlashpostRequest flashpostRequest){
        return requestManager.post(flashPostsEndpoint, flashpostRequest);
    }

    public int getNumberOfFlashposts(){
        return getFlashposts().jsonPath().getList("").size();
    }
}
