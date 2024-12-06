package org.example;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EmailField extends CustomField<String> {
    TextField emailtext = new TextField();
    String[] emailArray = {"@gmail.com", "@naver.com", "@daum.net", "@ajou.ac.kr"};
    private final List<String> emailList = Arrays.stream(emailArray).toList();
    private final Select<String> emailselect = new Select<>();
    public EmailField()
    {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().add("spacing-xs");

        layout.add(emailtext);

        emailselect.setItems(emailList);
        emailselect.setPlaceholder("@example.com");
        layout.add(emailselect);

        add(layout);
    }
    @Override
    protected String generateModelValue(){
        String emailValue = emailtext.getValue();
        String emailkind = emailselect.getValue();

        return emailValue+emailkind;
    }
    @Override
    protected void setPresentationValue(String expiration){}

    @Override
    public void clear(){
        emailtext.clear();
        emailselect.clear();
    }
}
