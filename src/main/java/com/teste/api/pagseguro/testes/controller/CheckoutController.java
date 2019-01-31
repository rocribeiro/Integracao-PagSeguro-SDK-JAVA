package com.teste.api.pagseguro.testes.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.checkout.CheckoutRegistrationBuilder;
import br.com.uol.pagseguro.api.checkout.RegisteredCheckout;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.common.domain.builder.AcceptedPaymentMethodsBuilder;
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
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
	//credenciais de sandbox
	String sellerEmailSand = "Meu Email";
	String sellerTokenSand = "Meu Token";
	//Credenciais de Produção
	String sellerEmailProd = "Meu Email";
	String sellerTokenProd = "Meu Token";
	
	CheckoutRegistrationBuilder cs = new CheckoutRegistrationBuilder().withCurrency(Currency.BRL)
			.withExtraAmount(BigDecimal.ONE).withReference("XXXXXX")
			.withSender(new SenderBuilder().withEmail("compradorRocribeiro@sandbox.pagseguro.com.br").withName("Jose Comprador")
					.withCPF("62718124067").withPhone(new PhoneBuilder().withAreaCode("11").withNumber("992898365")))
			.withShipping(new ShippingBuilder().withType(ShippingType.Type.SEDEX).withCost(BigDecimal.TEN)
					.withAddress(new AddressBuilder().withPostalCode("99999999").withCountry("BRA").withState(State.SP)
							.withCity("Cidade Exemplo").withComplement("99o andar").withDistrict("Jardim Internet")
							.withNumber("9999").withStreet("Av. PagSeguro")))

			.addItem(new PaymentItemBuilder().withId("0001").withDescription("Produto PagSeguroI")
					.withAmount(new BigDecimal(0.99)).withQuantity(1).withWeight(1000))

			.addItem(new PaymentItemBuilder().withId("0002").withDescription("Produto PagSeguroII")
					.withAmount(new BigDecimal(0.98)).withQuantity(2).withWeight(750))

			/*
			 * .withAcceptedPaymentMethods(new AcceptedPaymentMethodsBuilder()
			 * .addInclude(new PaymentMethodBuilder() .withGroup(PaymentMethodGroup.BALANCE)
			 * ) .addInclude(new PaymentMethodBuilder()
			 * .withGroup(PaymentMethodGroup.BANK_SLIP) ) )
			 */
			.addPaymentMethodConfig(new PaymentMethodConfigBuilder()
					.withPaymentMethod(new PaymentMethodBuilder().withGroup(PaymentMethodGroup.CREDIT_CARD)).withConfig(
							new ConfigBuilder().withKey(ConfigKey.DISCOUNT_PERCENT).withValue(new BigDecimal(10.00))))
			.addPaymentMethodConfig(new PaymentMethodConfigBuilder()
					.withPaymentMethod(new PaymentMethodBuilder().withGroup(PaymentMethodGroup.BANK_SLIP)).withConfig(
							new ConfigBuilder().withKey(ConfigKey.DISCOUNT_PERCENT).withValue(new BigDecimal(10.00))))

			.addPaymentMethodConfig(new PaymentMethodConfigBuilder()
					.withPaymentMethod(new PaymentMethodBuilder().withGroup(PaymentMethodGroup.CREDIT_CARD))
					.withConfig(new ConfigBuilder().withKey(ConfigKey.MAX_INSTALLMENTS_NO_INTEREST)
							.withValue(new BigDecimal(5))));

	@GetMapping("/lightbox/prod")
	public void lightboxProd(HttpServletResponse res) {
		try {
			
			final PagSeguro pagSeguro = PagSeguro.instance(new SimpleLoggerFactory(), new JSEHttpClient(),
					Credential.sellerCredential(sellerEmailProd, sellerTokenProd), PagSeguroEnv.PRODUCTION);

			RegisteredCheckout registeredCheckout = pagSeguro.checkouts().register(cs);
			System.out.println(registeredCheckout.getCheckoutCode());

			PrintWriter out = res.getWriter();
			out.print(registeredCheckout.getCheckoutCode());
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@GetMapping("/lightbox/sand")
	public void lightboxSand(HttpServletResponse res) {
		try {
			
			final PagSeguro pagSeguro = PagSeguro.instance(new SimpleLoggerFactory(), new JSEHttpClient(),
					Credential.sellerCredential(sellerEmailSand, sellerTokenSand), PagSeguroEnv.SANDBOX);

			RegisteredCheckout registeredCheckout = pagSeguro.checkouts().register(cs);
			System.out.println(registeredCheckout.getCheckoutCode());

			PrintWriter out = res.getWriter();
			out.print(registeredCheckout.getCheckoutCode());
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/redirect/prod")
	public void checkoutProd(HttpServletResponse res) {
		try {
			
			final PagSeguro pagSeguro = PagSeguro.instance(new SimpleLoggerFactory(), new JSEHttpClient(),
					Credential.sellerCredential(sellerEmailProd, sellerTokenProd), PagSeguroEnv.PRODUCTION);

			RegisteredCheckout registeredCheckout = pagSeguro.checkouts()
					.register(cs);
			System.out.println(registeredCheckout.getCheckoutCode());	

			PrintWriter out = res.getWriter();
			out.print(registeredCheckout.getRedirectURL());
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@GetMapping("/redirect/sand")
	public void checkoutSand(HttpServletResponse res) {
		try {
			
			final PagSeguro pagSeguro = PagSeguro.instance(new SimpleLoggerFactory(), new JSEHttpClient(),
					Credential.sellerCredential(sellerEmailSand, sellerTokenSand), PagSeguroEnv.SANDBOX);

			RegisteredCheckout registeredCheckout = pagSeguro.checkouts()
					.register(cs);
			System.out.println(registeredCheckout.getCheckoutCode());	

			PrintWriter out = res.getWriter();
			out.print(registeredCheckout.getRedirectURL());
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
