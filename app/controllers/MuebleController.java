package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Ciudad;
import models.Mueble;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by scvalencia606 on 8/10/15.
 */
public class MuebleController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = Controller.request().body().asJson();
        Mueble mueble = Mueble.bind(j);
        mueble.save();
        return ok(Json.toJson(mueble));
    }

    public Result readAll() {
        List<Mueble> muebles = new Model.Finder(String.class, Mueble.class).all();
        return ok(Json.toJson(muebles));
    }

    public Result readPaged(Long page, Long size) {
        int pageInt = page.intValue();
        int sizeInt = size.intValue();
        List<Mueble> muebles = new Model.Finder(String.class, Mueble.class).findPagedList(pageInt,sizeInt).getList();
        return ok(Json.toJson(muebles));
    }


   //  public Result read() {
   //     int cantidadMuebles = new Model.Finder(String.class, Mueble.class).findRowCount();
   //     List<Mueble> muebles;
   //     if( cantidadMuebles < 100){
   //          muebles = new Model.Finder(String.class, Mueble.class).findList().subList(1,cantidadMuebles);
   //     }
   //     else {
   //         muebles = new Model.Finder(String.class, Mueble.class).findList().subList(1, 100);
   //     }
   //     return ok(Json.toJson(muebles));
   // }
}
