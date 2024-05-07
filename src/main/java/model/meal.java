package model;

public class meal {
    private Long mealid;

    private String name;

    private String recipe;

    private String procedure;
    
    private String duration;

    private String difficulty;
    
    private String categories;

    public meal() {
    }

    public meal(Long mealid, String name, String recipe, String procedure, String duration, String difficulty, String categories) {
        this.mealid = mealid;
        this.name = name;
        this.recipe = recipe;
        this.procedure = procedure;
        this.duration = duration;
        this.difficulty = difficulty;
        this.categories = categories;
    }

    public Long getMealid() {
        return mealid;
    }

    public void setMealid(Long mealid) {
        this.mealid = mealid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "meal{" +
                "mealid=" + mealid +
                ", name='" + name + '\'' +
                ", recipe='" + recipe + '\'' +
                ", procedure='" + procedure + '\'' +
                ", duration='" + duration + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", categories='" + categories + '\'' +
                '}';
    }
}
