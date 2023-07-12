import { VerticalFeatureRow } from '../feature/VerticalFeatureRow';
import { Section } from '../layout/Section';

const VerticalFeatures = () => (
  <Section

  title="Advanced Features for Enterprise"
  description="Looking to elevate your voucher strategies? Flash sales vouchers to create urgency and boost conversions.
  Double spending capabilities for enhanced customer engagement.
  Diverse promotion types, including gift cards, cart promotions, loyalty programs, and more."

    
  >
    <VerticalFeatureRow
      title="Seamless Voucher Delivery"
      description="Deliver vouchers to your customers with ease, ensuring a smooth experience through channels such as email, SMS, or in-app notifications.

      "
      image="/assets/images/feature.svg"
      imageAlt="First feature alt text"
    />
    <VerticalFeatureRow
      title="Comprehensive Reporting and Insights"
      description="Gain valuable insights into the effectiveness of your campaigns with intuitive graphical reports, empowering you to make data-driven decisions and optimize your voucher strategies.

      "
      image="/assets/images/feature2.svg"
      imageAlt="Second feature alt text"
      reverse
    />
    <VerticalFeatureRow
      title="Streamlined Voucher Creation"
      description="Effortlessly create captivating campaigns and generate unique voucher codes tailored to your business objectives within a user-friendly interface.
  
      "
      image="/assets/images/feature3.svg"
      imageAlt="Third feature alt text"
    />
  </Section>
);

export { VerticalFeatures };
