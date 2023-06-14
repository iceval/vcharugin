package org.rosatom.vcharugin.components;

import org.rosatom.vcharugin.ModelRequest;
import org.rosatom.vcharugin.ModelResponse;
import org.rosatom.vcharugin.models.Model;

import java.text.Format;
import java.text.SimpleDateFormat;

public class ModelMapper {

    public static Model convertToModel(ModelRequest modelRequest) {
        Model model = new Model();
        model.setId(modelRequest.getId().equals("") ? null : modelRequest.getId());
        model.setName(modelRequest.getName());
        model.setComment(modelRequest.getComment());
        return model;
    }

    public static ModelResponse convertToModelResponse(Model model) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ModelResponse modelResponse = ModelResponse
                .newBuilder()
                .setId(model.getId())
                .setName(model.getName())
                .setComment(model.getComment())
                .setCreateAt(formatter.format(model.getCreateAt()))
                .setUpdateAt(formatter.format(model.getUpdateAt()))
                .setCreator(model.getCreator())
                .setUpdater(model.getUpdater())
                .build();
        return modelResponse;
    }

}
