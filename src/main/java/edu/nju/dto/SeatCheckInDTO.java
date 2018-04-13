package edu.nju.dto;

/**
 * @author Shenmiu
 * @date 2018/03/26
 * <p>
 * 座位检票登记的数据传输对象
 */
public class SeatCheckInDTO {

    private Integer venuePlanId;

    private RowAndColumnDTO rowAndColumnDTO;

    public Integer getVenuePlanId() {
        return venuePlanId;
    }

    public void setVenuePlanId(Integer venuePlanId) {
        this.venuePlanId = venuePlanId;
    }

    public RowAndColumnDTO getRowAndColumnDTO() {
        return rowAndColumnDTO;
    }

    public void setRowAndColumnDTO(RowAndColumnDTO rowAndColumnDTO) {
        this.rowAndColumnDTO = rowAndColumnDTO;
    }

    @Override
    public String toString() {
        return "SeatCheckInDTO{" +
                "venuePlanId=" + venuePlanId +
                ", rowAndColumnDTO=" + rowAndColumnDTO +
                '}';
    }
}
