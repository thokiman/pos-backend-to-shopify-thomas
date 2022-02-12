package cn.leadpad.pospal.query;

public class CategoryProductData {

    private long categoryUid;
    private long categoryParentUid;
    private String name; // fresh fish


    public CategoryProductData(long categoryUid, long categoryParentUid, String name) {
        this.categoryUid = categoryUid;
        this.categoryParentUid = categoryParentUid;
        this.name = name;
    }

    public long getCategoryUid() {
        return categoryUid;
    }

    public long getCategoryParentUid() {
        return categoryParentUid;
    }

    public String getName() {
        return name;
    }


}
