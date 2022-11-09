package core.controller;

import core.api.service.IllnessService;
import core.model.Illness;
import dto.external.IllnessDto;
import dto.internal.IllnessInternalDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/illnesses")
public class IllnessController {

    private IllnessService illnessService;
    private ModelMapper modelMapper;

    @GetMapping("/{illness_id}")
    public IllnessDto get(@PathVariable(name="illness_id") Long illnessId){
        return Optional.of(illnessId)
                .map(illnessService::getAndInitialize)
                .map(current->modelMapper.map(current, IllnessDto.class))
                .orElseThrow();
    }

    @PostMapping("/admin")
    public IllnessDto create(@RequestBody IllnessInternalDto illnessJson){
        return Optional.ofNullable(illnessJson)
                .map(current->modelMapper.map(current, Illness.class))
                .map(illnessService::create)
                .map(current->modelMapper.map(current, IllnessDto.class))
                .orElseThrow();
    }

    @PutMapping("/{illness_id}/update")
    public IllnessDto update(@PathVariable(name="illness_id") Long illnessId, @RequestBody IllnessInternalDto illnessJson){
        return Optional.ofNullable(illnessJson)
                .map(current->modelMapper.map(current, Illness.class))
                .map(toUpdate->illnessService.update(illnessId, toUpdate))
                .map(current->modelMapper.map(current, IllnessDto.class))
                .orElseThrow();
    }

    @DeleteMapping("/{illness_id}/admin/delete")
    public void delete(@PathVariable(name="illness_id") Long illnessId){
        illnessService.delete(illnessId);
    }
}
