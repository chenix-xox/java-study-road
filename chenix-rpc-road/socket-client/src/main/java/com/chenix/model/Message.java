package com.chenix.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Chenix
 */
@Data
@AllArgsConstructor
public class Message implements Serializable {
    private String content;
}