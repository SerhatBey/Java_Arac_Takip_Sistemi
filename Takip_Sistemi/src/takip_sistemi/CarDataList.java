
package takip_sistemi;


public class CarDataList {
    private int  dt_arcid;
    private String dt_plaka;
    private String dt_renk;
    private String dt_marka;
    private String dt_arc_yeni_drm;
    private String dt_arc_topkm;
    private String dt_arc_kazadurumu;
    private String dt_arc_kulsay;
    private String dt_arc_kyttrh;

    public CarDataList(String dt_plaka, String dt_renk, String dt_marka, String dt_arc_yeni_drm, String dt_arc_topkm, String dt_arc_kazadurumu, String dt_arc_kulsay, String dt_arc_kyttrh,int dt_arcid) {
        this.dt_arcid = dt_arcid;
        this.dt_plaka = dt_plaka;
        this.dt_renk = dt_renk;
        this.dt_marka = dt_marka;
        this.dt_arc_yeni_drm = dt_arc_yeni_drm;
        this.dt_arc_topkm = dt_arc_topkm;
        this.dt_arc_kazadurumu = dt_arc_kazadurumu;
        this.dt_arc_kulsay = dt_arc_kulsay;
        this.dt_arc_kyttrh = dt_arc_kyttrh;
    }

    /**
     * @return the dt_arcid
     */
    public int getDt_arcid() {
        return dt_arcid;
    }

    /**
     * @param dt_arcid the dt_arcid to set
     */
    public void setDt_arcid(int dt_arcid) {
        this.dt_arcid = dt_arcid;
    }

    /**
     * @return the dt_plaka
     */
    public String getDt_plaka() {
        return dt_plaka;
    }

    /**
     * @param dt_plaka the dt_plaka to set
     */
    public void setDt_plaka(String dt_plaka) {
        this.dt_plaka = dt_plaka;
    }

    /**
     * @return the dt_renk
     */
    public String getDt_renk() {
        return dt_renk;
    }

    /**
     * @param dt_renk the dt_renk to set
     */
    public void setDt_renk(String dt_renk) {
        this.dt_renk = dt_renk;
    }

    /**
     * @return the dt_marka
     */
    public String getDt_marka() {
        return dt_marka;
    }

    /**
     * @param dt_marka the dt_marka to set
     */
    public void setDt_marka(String dt_marka) {
        this.dt_marka = dt_marka;
    }

    /**
     * @return the dt_arc_yeni_drm
     */
    public String getDt_arc_yeni_drm() {
        return dt_arc_yeni_drm;
    }

    /**
     * @param dt_arc_yeni_drm the dt_arc_yeni_drm to set
     */
    public void setDt_arc_yeni_drm(String dt_arc_yeni_drm) {
        this.dt_arc_yeni_drm = dt_arc_yeni_drm;
    }

    /**
     * @return the dt_arc_topkm
     */
    public String getDt_arc_topkm() {
        return dt_arc_topkm;
    }

    /**
     * @param dt_arc_topkm the dt_arc_topkm to set
     */
    public void setDt_arc_topkm(String dt_arc_topkm) {
        this.dt_arc_topkm = dt_arc_topkm;
    }

    /**
     * @return the dt_arc_kazadurumu
     */
    public String getDt_arc_kazadurumu() {
        return dt_arc_kazadurumu;
    }

    /**
     * @param dt_arc_kazadurumu the dt_arc_kazadurumu to set
     */
    public void setDt_arc_kazadurumu(String dt_arc_kazadurumu) {
        this.dt_arc_kazadurumu = dt_arc_kazadurumu;
    }

    /**
     * @return the dt_arc_kulsay
     */
    public String getDt_arc_kulsay() {
        return dt_arc_kulsay;
    }

    /**
     * @param dt_arc_kulsay the dt_arc_kulsay to set
     */
    public void setDt_arc_kulsay(String dt_arc_kulsay) {
        this.dt_arc_kulsay = dt_arc_kulsay;
    }

    /**
     * @return the dt_arc_kyttrh
     */
    public String getDt_arc_kyttrh() {
        return dt_arc_kyttrh;
    }

    /**
     * @param dt_arc_kyttrh the dt_arc_kyttrh to set
     */
    public void setDt_arc_kyttrh(String dt_arc_kyttrh) {
        this.dt_arc_kyttrh = dt_arc_kyttrh;
    }

    
}
