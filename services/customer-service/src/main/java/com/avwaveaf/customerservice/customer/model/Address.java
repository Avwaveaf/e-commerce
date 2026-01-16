package com.avwaveaf.customerservice.customer.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class Address {
    private String alamat;
    private String kelurahan;
    private String kecamatan;
    private String kota;
    private String provinsi;
    private String kodepos;
}
