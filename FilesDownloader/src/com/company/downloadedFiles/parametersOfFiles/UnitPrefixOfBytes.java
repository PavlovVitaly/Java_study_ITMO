package com.company.downloadedFiles.parametersOfFiles;

/**
 * Created by владелец on 03.05.2017.
 * <div>Перечисление единиц измерения информации.</div>
 */
public enum UnitPrefixOfBytes {
    B, kB, MB, GB;

    public static UnitPrefixOfBytes setPrefix(int num){
        switch (num){
            case 0:
                return UnitPrefixOfBytes.B;
            case 1:
                return UnitPrefixOfBytes.kB;
            case 2:
                return UnitPrefixOfBytes.MB;
            case 3:
                return UnitPrefixOfBytes.GB;
        }
        return UnitPrefixOfBytes.B;
    }
}
