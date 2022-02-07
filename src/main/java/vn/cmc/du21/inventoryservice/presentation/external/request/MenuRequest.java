package vn.cmc.du21.inventoryservice.presentation.external.request;

public class MenuRequest {
    private String menuName;
    private long userId;

    public MenuRequest() {
    }

    public MenuRequest(String menuName, long userId) {
        this.menuName = menuName;
        this.userId = userId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
