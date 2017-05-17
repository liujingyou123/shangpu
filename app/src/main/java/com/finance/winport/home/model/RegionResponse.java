package com.finance.winport.home.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/5/17.
 */

public class RegionResponse extends BaseResponse{


    /**
     * blockList : [{"blockId":"139","blockName":"董家渡"},{"blockId":"140","blockName":"打浦桥"},{"blockId":"141","blockName":"淮海中路"},{"blockId":"142","blockName":"黄浦滨江"},{"blockId":"143","blockName":"老西门"},{"blockId":"144","blockName":"南京东路"},{"blockId":"145","blockName":"蓬莱公园"},{"blockId":"146","blockName":"人民广场"},{"blockId":"147","blockName":"世博滨江"},{"blockId":"148","blockName":"五里桥"},{"blockId":"149","blockName":"新天地"},{"blockId":"150","blockName":"豫园"}]
     * regionId : 310101
     * regionName : 黄浦区
     */

    private List<Region> data;

    public List<Region> getData() {
        return data;
    }

    public void setData(List<Region> data) {
        this.data = data;
    }

    public static class Region {
        private String regionId;
        private String regionName;
        private boolean isChecked;
        /**
         * blockId : 139
         * blockName : 董家渡
         */

        private List<Block> blockList;

        public String getRegionId() {
            return regionId;
        }

        public void setRegionId(String regionId) {
            this.regionId = regionId;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public List<Block> getBlockList() {
            return blockList;
        }

        public void setBlockList(List<Block> blockList) {
            this.blockList = blockList;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public static class Block {
            private String blockId;
            private String blockName;
            private boolean isChecked;

            public String getBlockId() {
                return blockId;
            }

            public void setBlockId(String blockId) {
                this.blockId = blockId;
            }

            public String getBlockName() {
                return blockName;
            }

            public void setBlockName(String blockName) {
                this.blockName = blockName;
            }

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }
        }
    }
}
