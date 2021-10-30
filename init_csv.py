import pandas as pd
import os.path
def convertCategory() :
    destination= 'src/main/resources/csvData/Products_Transformed.csv'
    if os.path.isfile(destination) is False:
        print("Products_Transformed.csv을 생성합니다.")

        origin_csv = pd.read_csv("src/main/resources/csvData/Products.csv", delimiter=',')
        result_csv = origin_csv.loc[:, ['category_large', 'data_id','name', 'price']]
        for e in range(result_csv.shape[0]):
            target_cat = result_csv.loc[e,'category_large']
            if target_cat == "과자":
                result_csv.loc[e,'category_large'] = 'SNACK'
            elif target_cat == "면류":
                result_csv.loc[e,'category_large'] = "NOODLE"
            elif target_cat =="상온HMR":
                result_csv.loc[e,'category_large'] = "HMR"
            elif target_cat =="생활용품":
                result_csv.loc[e,'category_large'] = "DAILY_NECESSITY"
            elif target_cat =="소스":
                result_csv.loc[e,'category_large'] = "SAUCE"
            elif target_cat =="유제품":
                result_csv.loc[e,'category_large'] = "DAIRY_PRODUCT"
            elif target_cat =="음료":
                result_csv.loc[e,'category_large'] = "DRINKS"
            elif target_cat =="의약외품":
                result_csv.loc[e,'category_large'] = "QUASI_DRUG"
            elif target_cat =="주류":
                result_csv.loc[e,'category_large'] = "ALCOHOL"
            elif target_cat =="커피차":
                result_csv.loc[e,'category_large'] = "CAFFEINE"
        print(result_csv)
        result_csv.to_csv(destination, index=False)

if __name__ == '__main__':
    convertCategory()