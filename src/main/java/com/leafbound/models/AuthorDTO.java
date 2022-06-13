package com.leafbound.models;

public class AuthorDTO {
    private int id;
    private String name;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    /**
     * 
     */
    public AuthorDTO() {
    }

    /**
     * @param name
     */
    public AuthorDTO(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AuthorDTO [id=" + id + ", name=" + name + "]";
    }

    /**
     * @param id
     * @param name
     */
    public AuthorDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
