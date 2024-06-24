package com.user.redex.business.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;
import java.util.List;

/**
 * @author Nabeel Ahmed
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "file", "files", "data" })
public class FileUploadRequest {

    @JsonProperty("file")
    private MultipartFile file;

    @JsonProperty("files")
    private List<MultipartFile> files;

    @JsonRawValue
    @JsonProperty("data")
    private String data;

    public FileUploadRequest() { }

    public FileUploadRequest(String data) { this.data = data; }

    public FileUploadRequest(MultipartFile file, String data) {
        this.file = file;
        this.data = data;
    }

    public FileUploadRequest(List<MultipartFile> files, String data) {
        this.files = files;
        this.data = data;
    }

    public MultipartFile getFile() {
        return file;
    }
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}