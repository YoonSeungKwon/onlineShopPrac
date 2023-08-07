package yoon.project.onlineShop.exception.sequnce;

import jakarta.validation.GroupSequence;

@GroupSequence({ValidationGroup.IdBlank.class, ValidationGroup.PasswordBlank.class, ValidationGroup.NameBlank.class})
public interface RegisterValidationSequence {
}
