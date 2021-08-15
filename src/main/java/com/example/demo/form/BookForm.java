package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.entity.Book;

import lombok.Data;

@Data
public class BookForm extends Book {
    
    @NotBlank(message = "タイトルを入力してください")
    @Size(max = 100, message = "タイトルは100文字以内で入力してください")
    private String title;

    @NotBlank(message = "内容を入力してください")
    @Size(max = 300, message = "内容は300文字以内で入力してください")
    private String content;
}
