package com.example.demo.service;

import com.example.demo.dto.ModelDto;
import com.example.demo.model.Model;
import com.example.demo.repository.ModelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;

    @Transactional
    public ModelDto.Response createResult(ModelDto.Create modelDto) {
        Model model = Model.builder()
                .modelName(modelDto.getModelName())
                .version(modelDto.getVersion())
                .path(modelDto.getPath())
                .build();

        Model savedModel = modelRepository.save(model);
        return toResponseDto(savedModel);
    }

    @Transactional(readOnly = true)
    public ModelDto.Response getModelById(Long id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found with id: " + id));
        return toResponseDto(model);
    }

    @Transactional(readOnly = true)
    public List<ModelDto.Response> getAllResults() {
        return modelRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteModel(Long id) {
        if (!modelRepository.existsById(id)) {
            throw new EntityNotFoundException("Result not found with id: " + id);
        }
        modelRepository.deleteById(id);
    }

    private ModelDto.Response toResponseDto(Model model) {
        return ModelDto.Response.builder()
                .id(model.getId())
                .modelName(model.getModelName())
                .version(model.getVersion())
                .path(model.getPath())
                .createdAt(model.getCreatedAt())
                .build();
    }
}
