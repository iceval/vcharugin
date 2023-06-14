package org.rosatom.vcharugin.services;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.rosatom.vcharugin.*;
import org.rosatom.vcharugin.exceptions.ModelException;
import org.rosatom.vcharugin.models.Model;
import org.rosatom.vcharugin.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.rosatom.vcharugin.components.ModelMapper.convertToModel;
import static org.rosatom.vcharugin.components.ModelMapper.convertToModelResponse;

@GrpcService
public class ModelService extends ModelServiceGrpc.ModelServiceImplBase{


    private final ModelRepository modelRepository;
    private ValidationService validationService;

    @Autowired
    public ModelService(ModelRepository modelRepository, ValidationService validationService) {
        this.modelRepository = modelRepository;
        this.validationService = validationService;
    }

    @Override
    public void createModel(ModelRequest request, StreamObserver<ModelResponse> responseObserver) {
        validationService.validateName(request.getName());
        validationService.validateComment(request.getComment());
        Model model = convertToModel(request);
        model.setCreateAt(new Date());
        model.setCreator(request.getName());
        model.setUpdateAt(new Date());
        model.setUpdater(request.getName());
        modelRepository.insert(model);
        responseObserver.onNext(convertToModelResponse(model));
        responseObserver.onCompleted();
    }

    @Override
    public void readModel(ModelRequest request, StreamObserver<ModelResponse> responseObserver) {
        Model foundModel = modelRepository.findById(request.getId())
                .orElseThrow(() -> new ModelException(ModelErrorCode.MODEL_NOT_FOUND));
        responseObserver.onNext(convertToModelResponse(foundModel));
        responseObserver.onCompleted();
    }

    @Override
    public void updateModel(ModelRequest request, StreamObserver<ModelResponse> responseObserver) {
        Model foundModel = modelRepository.findById(request.getId())
                .orElseThrow(() -> new ModelException(ModelErrorCode.MODEL_NOT_FOUND));
        validationService.validateName(request.getName());
        validationService.validateComment(request.getComment());
        foundModel.setName(request.getName());
        foundModel.setComment(request.getComment());
        foundModel.setUpdateAt(new Date());
        foundModel.setUpdater(request.getName());
        modelRepository.save(foundModel);
        responseObserver.onNext(convertToModelResponse(foundModel));
        responseObserver.onCompleted();
    }

    @Override
    public void deleteModel(ModelRequest request, StreamObserver<ModelResponse> responseObserver) {
        Model foundModel = modelRepository.findById(request.getId())
                .orElseThrow(() -> new ModelException(ModelErrorCode.MODEL_NOT_FOUND));
        modelRepository.deleteById(request.getId());
        responseObserver.onNext(convertToModelResponse(foundModel));
        responseObserver.onCompleted();
    }



}
