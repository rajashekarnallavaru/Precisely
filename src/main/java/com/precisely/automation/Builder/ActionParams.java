package com.precisely.automation.Builder;

public class ActionParams {
    private String byType;
    private String fileName;
    private String locator;
    private int position;
    private int waitTimeInSec;
    private Boolean byJS;
    private String text;
    private String attributeName;
    private Boolean returnKey; // not sure about this one

    public ActionParams(String byType, // EC input
                        String fileName, String locator, // EC input
                        int position, // EC input
                        int waitTimeInSec, // EC input
                        Boolean byJS, // output
                        String text, // output
                        String attributeName, // output
                        Boolean returnKey // output
    ) {
        this.byType = byType;
        this.fileName = fileName;
        this.locator = locator;
        this.position = position;
        this.waitTimeInSec = waitTimeInSec;
        this.byJS = byJS;
        this.text = text;
        this.attributeName = attributeName;
        this.returnKey = returnKey;
    }

    public ActionParams() {

    }

    public static class Params { // builder
        private String getByType;
        private String getFileName = "";
        private String getLocator;
        private int getPosition = 0;
        private int getWaitTimeInSec = 20;
        private Boolean getByJS = false;
        private String getText = "";
        private String getAttributeName = "";
        private Boolean getReturnKey = false;

        public Params() {
        }

        public Params setByType(String byType) {
            this.getByType = byType;
            return this;
        }

        public String getByType() {
            return getByType;
        }

        public Params setFileName(String fileName) {
            this.getFileName = fileName;
            return this;
        }

        public String getFileName() {
            return getFileName;
        }

        public Params setLocator(String locator) {
            this.getLocator = locator;
            return this;
        }

        public String getLocator() {
            return getLocator;
        }

        public Params setPosition(int position) {
            this.getPosition = position;
            return this;
        }

        public int getPosition() {
            return getPosition;
        }

        public Params setWaitTimeInSec(int waitTimeInSec) {
            this.getWaitTimeInSec = waitTimeInSec;
            return this;
        }

        public int getWaitTimeInSec() {
            return getWaitTimeInSec;
        }

        public Params setByJS(Boolean byJS) {
            this.getByJS = byJS;
            return this;
        }

        public Boolean getByJS() {
            return getByJS;
        }

        public Params setText(String text) {
            this.getText = text;
            return this;
        }

        public String getText() {
            return getText;
        }

        public Params setAttributeName(String attributeName) {
            this.getAttributeName = attributeName;
            return this;
        }

        public String getAttributeName() {
            return getAttributeName;
        }

        public Params setReturnKey(Boolean returnKey) {
            this.getReturnKey = returnKey;
            return this;
        }

        public Boolean getReturnKey() {
            return getReturnKey;
        }

        public ActionParams build() {
            return new ActionParams(getByType, getFileName, getLocator, getPosition, getWaitTimeInSec, getByJS, getText,
                    getAttributeName, getReturnKey);
        }
    }
}
