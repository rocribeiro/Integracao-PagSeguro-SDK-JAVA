package com.teste.api.pagseguro.testes.dados.checkout;

import java.math.BigDecimal;

import br.com.uol.pagseguro.api.checkout.CheckoutRegistrationBuilder;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ConfigBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodConfigBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ShippingBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.ConfigKey;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.common.domain.enums.PaymentMethodGroup;
import br.com.uol.pagseguro.api.common.domain.enums.State;

public class DadosCheckoutBuilder {
	
	public CheckoutRegistrationBuilder dadosCheckout() {
		CheckoutRegistrationBuilder cr = new CheckoutRegistrationBuilder()
     .withCurrency(Currency.BRL)
     .withExtraAmount(BigDecimal.ONE)
     .withReference("XXXXXX")
     .withSender(new SenderBuilder()
         .withEmail("comprador@uol.com.br")
         .withName("Jose Comprador")
         .withCPF("62718124067")
         .withPhone(new PhoneBuilder()
             .withAreaCode("11")
             .withNumber("992898365")))
     .withShipping(new ShippingBuilder()
         .withType(ShippingType.Type.SEDEX)
         .withCost(BigDecimal.TEN)
         .withAddress(new AddressBuilder()
             .withPostalCode("99999999")
             .withCountry("BRA")
             .withState(State.SP)
             .withCity("Cidade Exemplo")
             .withComplement("99o andar")
             .withDistrict("Jardim Internet")
             .withNumber("9999")
             .withStreet("Av. PagSeguro")))

     .addItem(new PaymentItemBuilder()
         .withId("0001")
         .withDescription("Produto PagSeguroI")
         .withAmount(new BigDecimal(0.99))
         .withQuantity(1)
         .withWeight(1000))

     .addItem(new PaymentItemBuilder()
         .withId("0002")
         .withDescription("Produto PagSeguroII")
         .withAmount(new BigDecimal(0.98))
         .withQuantity(2)
         .withWeight(750)
     )

  /*   .withAcceptedPaymentMethods(new AcceptedPaymentMethodsBuilder()
         .addInclude(new PaymentMethodBuilder()
             .withGroup(PaymentMethodGroup.BALANCE)
         )
         .addInclude(new PaymentMethodBuilder()
             .withGroup(PaymentMethodGroup.BANK_SLIP)
         )
     )
*/
     .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
         .withPaymentMethod(new PaymentMethodBuilder()
             .withGroup(PaymentMethodGroup.CREDIT_CARD)
         )
         .withConfig(new ConfigBuilder()
             .withKey(ConfigKey.DISCOUNT_PERCENT)
             .withValue(new BigDecimal(10.00))
         )
     )
     .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
         .withPaymentMethod(new PaymentMethodBuilder()
             .withGroup(PaymentMethodGroup.BANK_SLIP)
         )
         .withConfig(new ConfigBuilder()
             .withKey(ConfigKey.DISCOUNT_PERCENT)
             .withValue(new BigDecimal(10.00))
         )
     )

     
     .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
         .withPaymentMethod(new PaymentMethodBuilder()
             .withGroup(PaymentMethodGroup.CREDIT_CARD)
         )
         .withConfig(new ConfigBuilder()
             .withKey(ConfigKey.MAX_INSTALLMENTS_NO_INTEREST)
             .withValue(new BigDecimal(5))
         )
     );
		return cr;
	}
}
