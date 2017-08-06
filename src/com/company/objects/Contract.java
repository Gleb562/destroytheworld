package com.company.objects;


/**
 * Created by Gleb on 024 24.09.16.
 */
public class Contract {

        private int id;
        private int profit;
        private String date;
        private String clientName;



        public Contract(int id, int profit, String date, String clientName) {
            this.id = id;
            this.profit = profit;
            this.date = date;
            this.clientName = clientName;
        }

        public Contract() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProfit() {
        return profit;
    }

        public void setProfit(int profit) {
        this.profit = profit;
    }

        public String getDate() {
        return date;
    }

        public void setDate(String date) {
        this.date = date;
    }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }
    }

