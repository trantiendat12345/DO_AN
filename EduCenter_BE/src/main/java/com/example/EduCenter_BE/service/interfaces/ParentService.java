package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.entity.Parent;
import com.example.EduCenter_BE.request.parent.CreateParentRequest;

public interface ParentService {

    Parent createParent(CreateParentRequest request);

    Long totalParentCount();

}
