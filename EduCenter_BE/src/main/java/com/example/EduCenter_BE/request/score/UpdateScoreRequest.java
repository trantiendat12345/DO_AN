package com.example.EduCenter_BE.request.score;

import com.example.EduCenter_BE.constant.enums.ScoreType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateScoreRequest {

    @NotNull(message = "Loại điểm không được để trống")
    private ScoreType type;

    @NotNull(message = "Điểm không được để trống")
    @Min(value = 0, message = "Điểm phải lớn hơn hoặc bằng 0")
    @Max(value = 10, message = "Điểm phải nhỏ hơn hoặc bằng 10")
    private Float value;

    private String note;
}
