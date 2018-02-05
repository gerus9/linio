package com.gerus.linio.models.deserializer;

import com.gerus.linio.models.Product;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by gerus-mac on 03/02/18.
 */

public class ProductDeserializer implements JsonDeserializer<List<Product>> {

    @Override
    public List<Product> deserialize(JsonElement json, Type typeOfSrc, JsonDeserializationContext context) throws JsonParseException {
        JsonObject voJsonProducts = json.getAsJsonObject();
        List<Product> voListProducts = new ArrayList<>();
        Set<Map.Entry<String, JsonElement>> voEntriesProducts = voJsonProducts.entrySet();
        for (Map.Entry<String, JsonElement> voEntryProduct: voEntriesProducts) {
            Product voProduct = new Gson().fromJson(voEntryProduct.getValue(), Product.class);
            voProduct.setIdJson(voEntryProduct.getKey());
            voListProducts.add(voProduct);
        }
        return voListProducts;
    }
}
